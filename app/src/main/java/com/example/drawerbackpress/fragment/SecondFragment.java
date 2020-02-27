package com.example.drawerbackpress.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.drawerbackpress.R;
import com.example.drawerbackpress.controller.BaseFragment;

public class SecondFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_fragment ,container ,false);
        return view;
    }

    @Override
    protected String screenName() {
        return "second_screen";
    }
}
