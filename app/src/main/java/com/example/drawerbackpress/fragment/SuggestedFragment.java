package com.example.drawerbackpress.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.api.ApiClient;
import com.api.ApiInterface;
import com.example.drawerbackpress.R;
import com.example.drawerbackpress.adapter.MoviesAdapter;
import com.example.drawerbackpress.adapter.TestAdapter;
import com.example.drawerbackpress.adapter.Testitem;
import com.example.drawerbackpress.controller.BaseFragment;
import com.example.drawerbackpress.model.Movie;
import com.example.drawerbackpress.model.MoviesResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuggestedFragment extends BaseFragment implements TestAdapter.onClick , MoviesAdapter.onCallBack {

    public static String ARG_TITLE = "title";


    public ArrayList<Testitem> testitemArrayList;

    // TODO - insert your themoviedb.org API KEY here
    private final static String API_KEY = "c31dce7dae483b752a1adfcb2a791674";

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

        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ApiInterface apiService = ApiClient.Companion.getClient().create(ApiInterface.class);

        Call<MoviesResponse> call = apiService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                List<Movie> movies = response.body().getResults();
                addIntoList(movies);
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {

            }
        });

        return view;
    }

    public void addIntoList(List<Movie> movieList){
        recyclerView.setAdapter(new MoviesAdapter(movieList, R.layout.list_item_movie, getActivity() , this));
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

    @Override
    public void onItemCall() {
        pushDetailFragment(new DetailsFragment());
    }
}
