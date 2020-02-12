package com.example.drawerbackpress.controller;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.lang.reflect.Field;

public abstract class BaseFragment extends BaseController{

    private static final int DEFAULT_CHILD_ANIMATION_DURATION = 250;
    protected NavigationEventRelay navigationEventRelay;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        navigationEventRelay = new NavigationEventRelay();
        super.onCreate(savedInstanceState);

    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {

        /*
         * When a fragment transaction is performed on a parent fragment containing nested children,
         * the children disappear as soon as the transaction begins.. So if you're relying on some
         * fancy animation for the parent, we need to ensure the child waits before performing its
         * own animation.
         * see https://code.google.com/p/android/issues/detail?id=55228
         */
        final Fragment parent = getParentFragment();

        // Apply the workaround only if this is a child fragment, and the parent
        // is being removed.
        if (!enter && parent != null && parent.isRemoving()) {
            // This is a workaround for the bug where child fragments disappear when
            // the parent is removed (as all children are first removed from the parent)
            // See https://code.google.com/p/android/issues/detail?id=55228
            Animation doNothingAnim = new AlphaAnimation(1, 1);
            doNothingAnim.setDuration(getNextAnimationDuration(parent, DEFAULT_CHILD_ANIMATION_DURATION));
            return doNothingAnim;
        } else {
            return super.onCreateAnimation(transit, enter, nextAnim);
        }
    }


    /**
     * Attempt to get the resource ID of the next animation that
     * will be applied to the given fragment.
     *
     * @param fragment the parent fragment
     * @param defValue default animation value
     * @return the duration of the parent fragment's animation
     */
    private static long getNextAnimationDuration(Fragment fragment, long defValue) {
        try {
            // Attempt to get the resource ID of the next animation that
            // will be applied to the given fragment.
            Field nextAnimField = Fragment.class.getDeclaredField("mNextAnim");
            nextAnimField.setAccessible(true);
            int nextAnimResource = nextAnimField.getInt(fragment);
            Animation nextAnim = AnimationUtils.loadAnimation(fragment.getActivity(), nextAnimResource);

            // ...and if it can be loaded, return that animation's duration
            return (nextAnim == null) ? defValue : nextAnim.getDuration();
        } catch (NoSuchFieldException | IllegalAccessException | Resources.NotFoundException ignored) {
            return defValue;
        }
    }


    public NavigationEventRelay getNavigationEventRelay() {
        return navigationEventRelay;
    }

    protected abstract String screenName();
}
