package com.example.drawerbackpress.listeners;

import androidx.annotation.NonNull;

public interface BackPressHandler {
    void addBackPressListener(@NonNull BackPressListener listener);
    void removeBackPressListener(@NonNull BackPressListener listener);
}
