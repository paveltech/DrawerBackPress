package com.example.drawerbackpress.controller;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentInfo {

    public Class rootViewController;
    @Nullable public transient Bundle args;
    public String rootViewControllerTag;

    public FragmentInfo(Class rootViewController, @Nullable Bundle args, String rootViewControllerTag) {
        this.rootViewController = rootViewController;
        this.rootViewControllerTag = rootViewControllerTag;
        this.args = args;
    }


    public Fragment instantiateFragment(Context context){
        return Fragment.instantiate(context , rootViewController.getName() , args);
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
