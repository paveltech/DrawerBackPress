package com.example.drawerbackpress.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.drawerbackpress.R;
import com.example.drawerbackpress.listeners.BackPressHandler;
import com.example.drawerbackpress.listeners.BackPressListener;
import com.example.drawerbackpress.listeners.NavigationController;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseNavigationController extends BaseController implements NavigationController<Fragment> {

    public abstract FragmentInfo getRootViewControllerInfo();
    private List<BackPressListener> backPressListeners = new ArrayList<>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.navigation_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null){
            addRootFragment();
        }
    }

    protected void addRootFragment() {
        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.mainContainer, getRootViewControllerInfo().instantiateFragment(getContext()), getRootViewControllerInfo().rootViewControllerTag)
                .commit();
    }

    @Override
    public void onResume() {
        super.onResume();

        if (getActivity() instanceof BackPressHandler){
            ((BackPressHandler) getActivity()).addBackPressListener(this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        if (getActivity() instanceof BackPressHandler) {
            ((BackPressHandler) getActivity()).removeBackPressListener(this);
        }
    }


    @Override
    public boolean consumeBackPress() {

        for (int i = backPressListeners.size()-1 ; i>=0 ; i--){
            if (backPressListeners.get(i).consumeBackPress()){
                return true;
            }
        }

        if (getChildFragmentManager().getBackStackEntryCount()>0){
            popViewController();
            return true;
        }

        return false;
    }


    @Override
    public void pushViewController(@NonNull Fragment fragment, @Nullable String tag, @NonNull List<Pair<View, String>> sharedElements) {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        if (sharedElements!=null){
            for (Pair<View , String> pair : sharedElements){
                fragmentTransaction.addSharedElement(pair.first , pair.second);
            }
        }

        fragmentTransaction.addToBackStack(null)
                .replace(R.id.mainContainer , fragment , tag).commit();
    }

    @Override
    public void pushViewController(@NonNull Fragment controller, @Nullable String tag) {
        pushViewController(controller , tag , null);
    }

    @Override
    public void popViewController() {
        getChildFragmentManager().popBackStack();
    }

    @Override
    public void popToRootViewController() {
        getChildFragmentManager().popBackStack(null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public void addBackPressListener(@NonNull BackPressListener listener) {
        if (!backPressListeners.contains(listener)){
            backPressListeners.add(listener);
        }
    }

    @Override
    public void removeBackPressListener(@NonNull BackPressListener listener) {
        if (backPressListeners.contains(listener)) {
            backPressListeners.remove(listener);
        }
    }
}
