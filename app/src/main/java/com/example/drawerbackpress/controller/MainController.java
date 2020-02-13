package com.example.drawerbackpress.controller;

import com.example.drawerbackpress.listeners.BackPressHandler;
import com.example.drawerbackpress.listeners.DrawerLockController;

public class MainController extends BaseNavigationController implements BackPressHandler , DrawerLockController {
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
