package com.example.menuwithsubmenu.ManagerMenus;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.menuwithsubmenu.R;


public class ManagerProfileFragment extends Fragment {
    Button postDish;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_manager_profile,null);
        getActivity().setTitle("Post Dish");


        postDish =  (Button)v.findViewById(R.id.post_dish);

        postDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent n= new Intent(getContext(), Manager_postDish.class);
                startActivity(n);

            }
        });

        return v;
    }
}
