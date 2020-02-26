package com.example.drawerbackpress.controller;

import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import com.cantrowitz.rxbroadcast.RxBroadcast;
import com.example.drawerbackpress.R;
import com.example.drawerbackpress.adapter.PagerAdapter;
import com.example.drawerbackpress.listeners.ContextualToolbarHost;
import com.example.drawerbackpress.listeners.ToolbarListener;
import com.example.drawerbackpress.pojo.CategoryItem;
import com.example.drawerbackpress.pojo.ViewBackgroundAction;
import com.example.drawerbackpress.utils.Rx;
import com.example.drawerbackpress.views.ContextualToolbar;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


import static com.example.drawerbackpress.utils.Rx.distinctToMainThread;

public class LibraryController extends BaseFragment implements ContextualToolbarHost {


    public static final String EVENT_TABS_CHANGED = "tabs_changed";

    @BindView(R.id.tabs)
    TabLayout slidingTabLayout;

    @BindView(R.id.pager)
    ViewPager pager;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.app_bar)
    AppBarLayout appBarLayout;

    private boolean refreshPagerAdapter = false;

    @BindView(R.id.contextualToolbar)
    ContextualToolbar contextualToolbar;


    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private Disposable tabChangedDisposable;
    private com.example.drawerbackpress.adapter.PagerAdapter pagerAdapter;

    public static FragmentInfo fragmentInfo() {
        return new FragmentInfo(LibraryController.class, null, "LibraryController");
    }

    public LibraryController() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_library, container, false);
        ButterKnife.bind(this, rootView);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        setupViewPager();
        return rootView;

    }




    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getActivity() instanceof ToolbarListener) {
            ((ToolbarListener) getActivity()).toolbarAttached(view.findViewById(R.id.toolbar));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        navigationEventRelay.sendEvent(new NavigationEventRelay.NavigationEvent(NavigationEventRelay.NavigationEvent.Type.LIBRARY_SELECTED, null, false));
    }


    private void setupViewPager() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        CategoryItem.getCategoryItems(sharedPreferences);

        if (pagerAdapter != null && refreshPagerAdapter) {
            pagerAdapter.removeAllChildFragments();
            refreshPagerAdapter = false;
            pager.setAdapter(null);
        }

        int defaultPage = 1;

        pagerAdapter = new PagerAdapter(getChildFragmentManager());

        List<CategoryItem> categoryItems = CategoryItem.getCategoryItems(sharedPreferences);




        for (int i = 0; i < categoryItems.size(); i++) {
            CategoryItem categoryItem = categoryItems.get(i);
            pagerAdapter.addFragment(categoryItem.getFragment(getContext()));
            if (categoryItem.type == 2) {
                defaultPage = i;
            }
        }

        int currentPage = Math.min(defaultPage, pagerAdapter.getCount());
        pager.setAdapter(pagerAdapter);
        pager.setOffscreenPageLimit(pagerAdapter.getCount() - 1);
        pager.setCurrentItem(currentPage);

        slidingTabLayout.setupWithViewPager(pager);

    }



    @Override
    public void onDestroyView() {
        pager.setAdapter(null);
        compositeDisposable.clear();
        super.onDestroyView();
    }


    @Override
    protected String screenName() {
        return null;
    }

    @Override
    public ContextualToolbar getContextualToolbar() {
        return contextualToolbar;
    }
}
