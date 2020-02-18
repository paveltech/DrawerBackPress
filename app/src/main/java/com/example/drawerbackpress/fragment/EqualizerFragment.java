package com.example.drawerbackpress.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.drawerbackpress.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EqualizerFragment extends Fragment {

    @BindView(R.id.child)
    TextView child;

    public static EqualizerFragment newInstance() {
        Bundle args = new Bundle();
        EqualizerFragment fragment = new EqualizerFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.child_fragment , container , false);
        ButterKnife.bind(this , view);
        child.setText("Equlizer fragment");
        return view;
    }
}
