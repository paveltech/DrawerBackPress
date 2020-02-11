package com.example.drawerbackpress;

import android.os.Build;
import android.os.Bundle;

import com.example.drawerbackpress.listeners.BackPressHandler;
import com.example.drawerbackpress.listeners.BackPressListener;
import com.example.drawerbackpress.listeners.DrawerProvider;
import com.example.drawerbackpress.listeners.ToolbarListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
ToolbarListener , BackPressHandler , DrawerProvider{

    private List<BackPressListener> backPressListeners = new ArrayList<>();
    private DrawerLayout drawerLayout;
    private View navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.navView);
        drawerLayout = findViewById(R.id.drawer_layout);

    }

    @Override
    public void addBackPressListener(@NonNull BackPressListener listener) {

    }

    @Override
    public void removeBackPressListener(@NonNull BackPressListener listener) {

    }

    @Override
    public DrawerLayout getDrawerLayout() {
        return null;
    }

    @Override
    public void toolbarAttached(Toolbar toolbar) {

    }
}
