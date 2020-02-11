package com.example.drawerbackpress.listeners;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;

import java.util.List;

public interface NavigationController<T> extends Controller<T>, BackPressHandler, BackPressListener {

    /**
     * Handle a back press click event from the {@link BackPressHandler}.
     *
     * @return true if the back press was consumed, else false.
     */

    @Override
    boolean consumeBackPress();

    /**
     * Push a {@link Controller} into this {@link NavigationController}
     *
     * @param controller the {@link Controller} to push
     * @param tag        a {@link String} used to identify this controller
     */


    void pushViewController(@NonNull T controller, @Nullable String tag);

    /**
     * Push a {@link Controller} into this {@link NavigationController}, with a shared element transition.
     *
     * @param controller  the {@link Controller} to push
     * @param tag         a {@link String} used to identify this controller
     * @param transitions a List of View/String pairs used for shared element transitions
     */
    void pushViewController(@NonNull T controller, @Nullable String tag, @NonNull List<Pair<View, String>> transitions);

    /**
     * Pop a single child controller off the stack.
     */
    void popViewController();

    /**
     * Pop all children off the stack, revealing the root view controller.
     */
    void popToRootViewController();
}
