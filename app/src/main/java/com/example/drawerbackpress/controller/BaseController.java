package com.example.drawerbackpress.controller;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.drawerbackpress.listeners.Controller;
import com.example.drawerbackpress.listeners.NavigationController;

public abstract class BaseController extends Fragment implements Controller<Fragment> {


    @Nullable
    @Override
    public NavigationController<Fragment> getNavigationController() {
        return findNavigationController(this);
    }

    /**
     * Traverses the fragment hierarchy searching for the first available {@link NavigationController}.
     * If none are found, then this method checks whether the parent {@link android.app.Activity} is
     * a {@link NavigationController} and returns that instead.
     *
     * @param fragment the fragment whose hierarchy will be searched.
     */

    public static NavigationController<Fragment> findNavigationController(Fragment fragment){
        Fragment parent = fragment.getParentFragment();

        if (parent instanceof NavigationController){
            return (NavigationController) parent;
        }

        if (parent!=null){
            return findNavigationController(parent);
        }else {
            if (fragment.getActivity() instanceof NavigationController){
                return (NavigationController) fragment.getActivity();
            }
        }

        throw new IllegalStateException("Couldn't find parent navigation controller.");
    }
}
