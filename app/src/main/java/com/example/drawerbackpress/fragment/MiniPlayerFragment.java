package com.example.drawerbackpress.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MiniPlayerFragment extends Fragment {

    public MiniPlayerFragment() {

    }

    public static MiniPlayerFragment newInstance() {
        MiniPlayerFragment fragment = new MiniPlayerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "Mini player fragment", Toast.LENGTH_SHORT).show();
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
