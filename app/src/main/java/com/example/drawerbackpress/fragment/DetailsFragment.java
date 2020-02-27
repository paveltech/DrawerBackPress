package com.example.drawerbackpress.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;

import com.example.drawerbackpress.R;
import com.example.drawerbackpress.controller.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsFragment extends BaseFragment {

    @BindView(R.id.name)
    TextView idName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_layout , container , false);
        ButterKnife.bind(this , view);

        idName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushDetailFragment(new SecondFragment());
            }
        });
        return view;
    }

    void pushDetailFragment(Fragment fragment) {
        List<Pair<View, String>> transitions = new ArrayList<>();
        getNavigationController().pushViewController(fragment, "DetailFragment", transitions);
    }


    @Override
    protected String screenName() {
        return "a";
    }
}
