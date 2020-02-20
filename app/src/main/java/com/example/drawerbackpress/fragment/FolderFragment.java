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

public class FolderFragment extends Fragment {

    @BindView(R.id.child)
    TextView child;
    public static String ARG_TITLE = "title";

    private static final String ARG_DISPLAYED_IN_TABS = "displayed_in_tabs";

    public static FolderFragment newInstance(String title, boolean isDisplayedInTabs) {
        FolderFragment fragment = new FolderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putBoolean(ARG_DISPLAYED_IN_TABS, isDisplayedInTabs);
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.child_fragment , container , false);
        ButterKnife.bind(this , view);
        child.setText(""+getArguments().getInt(ARG_TITLE));
        return view;
    }
}
