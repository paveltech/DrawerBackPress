package com.example.drawerbackpress.controller;

import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.cantrowitz.rxbroadcast.RxBroadcast;
import com.example.drawerbackpress.R;
import com.example.drawerbackpress.event.MultiSheetEventRelay;
import com.example.drawerbackpress.event.MultiSheetSlideEventRelay;
import com.example.drawerbackpress.fragment.ArtistDetailFragment;
import com.example.drawerbackpress.fragment.EqualizerFragment;
import com.example.drawerbackpress.fragment.MiniPlayerFragment;
import com.example.drawerbackpress.fragment.PlayerFragment;
import com.example.drawerbackpress.listeners.BackPressHandler;
import com.example.drawerbackpress.listeners.DrawerLockController;
import com.example.drawerbackpress.listeners.InternalIntents;

import com.example.drawerbackpress.sheet.DrawerLockManager;
import com.example.drawerbackpress.sheet.MultiSheetView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainController extends BaseNavigationController implements BackPressHandler , DrawerLockController {

    private static final String TAG = "MainController";

    public static final String STATE_CURRENT_SHEET = "current_sheet";
    public NavigationEventRelay navigationEventRelay;
    public MultiSheetEventRelay multiSheetEventRelay;
    public MultiSheetSlideEventRelay multiSheetSlideEventRelay;

    private Handler delayHandler;



    private CompositeDisposable disposables = new CompositeDisposable();

    public static MainController newInstance() {
        Bundle args = new Bundle();
        MainController fragment = new MainController();
        fragment.setArguments(args);
        return fragment;
    }

    public MainController() {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        navigationEventRelay = new NavigationEventRelay();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (delayHandler != null) {
            delayHandler.removeCallbacksAndMessages(null);
        }
        delayHandler = new Handler();
        disposables.add(navigationEventRelay.getEvents()
        .observeOn(AndroidSchedulers.mainThread())
        .filter(NavigationEventRelay.NavigationEvent::isActionable)
        .subscribe(navigationEvent ->{
            switch (navigationEvent.type){
                case NavigationEventRelay.NavigationEvent.Type.LIBRARY_SELECTED:
                    popToRootViewController();
                    break;


                case NavigationEventRelay.NavigationEvent.Type.EQUALIZER_SELECTED:
                    delayHandler.postDelayed(
                            () -> multiSheetEventRelay.sendEvent(
                                    new MultiSheetEventRelay.MultiSheetEvent(
                                            MultiSheetEventRelay.MultiSheetEvent.Action.HIDE ,
                                            MultiSheetView.Sheet.FIRST)), 100
                    );
                    delayHandler.postDelayed(()-> pushViewController(EqualizerFragment.newInstance("Equalizer"), "Equalizer fragment"), 250);
                    break;


                case NavigationEventRelay.NavigationEvent.Type.GO_TO_ARTIST:
                    String id = (String) navigationEvent.data;
                    delayHandler.postDelayed(() -> {
                        popToRootViewController();
                        pushViewController(ArtistDetailFragment.newInstance(id, null), "ArtistDetailFragment");
                    }, 250);
                    break;

            }
        }));


        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(InternalIntents.SERVICE_CONNECTED);
        intentFilter.addAction(InternalIntents.QUEUE_CHANGED);
        disposables.add(
                RxBroadcast.fromBroadcast(getContext(), intentFilter)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(intent -> {
                            //toggleBottomSheetVisibility(true, true);
                        })
        );


    }


    @Override
    public void onPause() {
        super.onPause();
        delayHandler.removeCallbacksAndMessages(null);
        delayHandler = null;
        disposables.clear();
        DrawerLockManager.getInstance().setDrawerLockController(null);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }



    @Override
    public void lockDrawer() {

    }

    @Override
    public void unlockDrawer() {

    }

    @Override
    public FragmentInfo getRootViewControllerInfo() {
        return LibraryController.fragmentInfo();
    }

    @Override
    public boolean consumeBackPress() {
        return super.consumeBackPress();
    }

    @Override
    public void pushViewController(@NonNull Fragment fragment, @Nullable String tag, @Nullable List<Pair<View, String>> sharedElements) {
        FragmentTransaction fragmentTransaction = getChildFragmentManager()
                .beginTransaction();

        if (sharedElements != null) {
            for (Pair<View, String> pair : sharedElements) {
                try {
                    fragmentTransaction.addSharedElement(pair.first, pair.second);
                } catch (IllegalArgumentException e) {
                }
            }
        }

        fragmentTransaction.addToBackStack(null)
                .replace(R.id.mainContainer, fragment, tag)
                .commit();
    }
}
