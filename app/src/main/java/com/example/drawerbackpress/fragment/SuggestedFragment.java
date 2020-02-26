package com.example.drawerbackpress.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drawerbackpress.R;
import com.example.drawerbackpress.adapter.TestAdapter;
import com.example.drawerbackpress.adapter.Testitem;
import com.example.drawerbackpress.controller.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SuggestedFragment extends BaseFragment implements TestAdapter.onClick {

    public static String ARG_TITLE = "title";


    public ArrayList<Testitem> testitemArrayList;

    public static SuggestedFragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        SuggestedFragment fragment = new SuggestedFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @BindView(R.id.recycleview)
    RecyclerView recyclerView;

    public TestAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.child_fragment, container, false);
        ButterKnife.bind(this, view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new TestAdapter(getActivity() , getTestitemArrayList() ,this);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    protected String screenName() {
        return "nothig";
    }

    void pushDetailFragment(Fragment fragment) {
        List<Pair<View, String>> transitions = new ArrayList<>();
        getNavigationController().pushViewController(fragment, "DetailFragment", transitions);
    }


    @Override
    public void clickItem() {
        pushDetailFragment(new DetailsFragment());
    }

    public ArrayList<Testitem> getTestitemArrayList() {
        testitemArrayList = new ArrayList();
        for (int i =0 ; i<=100; i++){
            testitemArrayList.add(new Testitem(""+i));
        }
        return testitemArrayList;
    }
}
