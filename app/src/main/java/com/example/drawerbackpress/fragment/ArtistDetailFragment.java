package com.example.drawerbackpress.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.drawerbackpress.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArtistDetailFragment extends Fragment {


    public static EqualizerFragment newInstance(String id , String transitionName) {
        Bundle args = new Bundle();
        EqualizerFragment fragment = new EqualizerFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @BindView(R.id.child)
    TextView child;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.child_fragment , container , false);
        ButterKnife.bind(this , view);

        Toast.makeText(getActivity() , "Show" , Toast.LENGTH_SHORT).show();

        child.setText("Equlizer fragment");
        return view;
    }
}
