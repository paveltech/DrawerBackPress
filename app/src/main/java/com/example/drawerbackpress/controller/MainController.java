package com.example.drawerbackpress.controller;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.drawerbackpress.R;
import com.example.drawerbackpress.event.MultiSheetEventRelay;
import com.example.drawerbackpress.event.MultiSheetSlideEventRelay;
import com.example.drawerbackpress.fragment.MiniPlayerFragment;
import com.example.drawerbackpress.fragment.PlayerFragment;
import com.example.drawerbackpress.listeners.BackPressHandler;
import com.example.drawerbackpress.listeners.DrawerLockController;
import com.example.drawerbackpress.sheet.CustomMultiSheetView;
import com.example.drawerbackpress.sheet.MultiSheetView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;

public class MainController extends BaseNavigationController implements BackPressHandler , DrawerLockController {

    private static final String TAG = "MainController";

    public static final String STATE_CURRENT_SHEET = "current_sheet";
    public NavigationEventRelay navigationEventRelay;
    public MultiSheetEventRelay multiSheetEventRelay;
    public MultiSheetSlideEventRelay multiSheetSlideEventRelay;

    private Handler delayHandler;


    @BindView(R.id.multiSheetView)
    CustomMultiSheetView multiSheetView;

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
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this , rootView);

        navigationEventRelay = new NavigationEventRelay();

        if (savedInstanceState == null){
            getChildFragmentManager()
                    .beginTransaction()
                    .add(multiSheetView.getSheetContainerViewResId(MultiSheetView.Sheet.FIRST), PlayerFragment.newInstance())
                    .add(multiSheetView.getSheetPeekViewResId(MultiSheetView.Sheet.FIRST), MiniPlayerFragment.newInstance())
                    .commit();

        }else {
            multiSheetView.restoreSheet(savedInstanceState.getInt(STATE_CURRENT_SHEET));
        }



        return rootView;

    }


    @Override
    public void onResume() {
        super.onResume();

        if (delayHandler != null) {
            delayHandler.removeCallbacksAndMessages(null);
        }
        delayHandler = new Handler();


    }

    @Override
    public FragmentInfo getRootViewControllerInfo() {
        return null;
    }

    @Override
    public void lockDrawer() {

    }

    @Override
    public void unlockDrawer() {

    }
}
