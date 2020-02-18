package com.example.drawerbackpress.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import com.example.drawerbackpress.listeners.ContextualToolbarHost;

public class ContextualToolbar extends Toolbar {

    public ContextualToolbar(Context context) {
        super(context);
    }

    public ContextualToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ContextualToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void show() {
        setVisibility(View.VISIBLE);
    }

    public void hide() {
        setVisibility(View.GONE);
    }

    /**
     * Traverses the fragment hierarchy, searching for an instance of {@link ContextualToolbarHost}
     *
     * @return {@link ContextualToolbar} or null if none can be found in the fragment hierarchy.
     */
    @Nullable
    public static ContextualToolbar findContextualToolbar(Fragment fragment) {
        if (fragment instanceof ContextualToolbarHost) {
            return ((ContextualToolbarHost) fragment).getContextualToolbar();
        } else {
            Fragment parentFragment = fragment.getParentFragment();
            if (parentFragment != null) {
                return findContextualToolbar(parentFragment);
            }
        }
        return null;
    }
}