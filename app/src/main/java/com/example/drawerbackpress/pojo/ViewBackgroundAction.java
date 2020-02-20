package com.example.drawerbackpress.pojo;

import android.annotation.SuppressLint;
import android.view.View;

import java.util.function.Consumer;

@SuppressLint("NewApi")
public class ViewBackgroundAction implements Consumer<Integer> {

    private final View view;


    private ViewBackgroundAction(View view){
        this.view = view;
    }

    public static ViewBackgroundAction create(View view){
        return new ViewBackgroundAction(view);
    }

    @Override
    public void accept(Integer color) {
        if (view!=null){
            view.setBackgroundColor(color);
        }
    }
}
