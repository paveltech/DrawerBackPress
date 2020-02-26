package com.example.drawerbackpress.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drawerbackpress.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.CustomView>{

    public Context context;
    public ArrayList<Testitem> testitemArrayList;
    public onClick onClick;


    public TestAdapter(Context context , ArrayList<Testitem> testitemArrayList, onClick onClick){
        this.context = context;
        this.onClick = onClick;
        this.testitemArrayList = testitemArrayList;
    }

    @NonNull
    @Override
    public CustomView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_layout , parent , false);
        return new CustomView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomView holder, int position) {
        Testitem testitem = testitemArrayList.get(position);
        holder.finalName.setText(testitem.getId());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.clickItem();
            }
        });
    }

    @Override
    public int getItemCount() {
        return testitemArrayList.size();
    }

    public class CustomView extends RecyclerView.ViewHolder{

        @BindView(R.id.name)
        TextView finalName;

        View view;

        public CustomView(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this , itemView);
        }
    }

    public interface onClick{
        void clickItem();
    }
}
