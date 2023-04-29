package com.example.menuwithsubmenu.CustomerMenus;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.menuwithsubmenu.Deleteuser;
import com.example.menuwithsubmenu.ManagerMenus.Manager_postDish;
import com.example.menuwithsubmenu.R;

public class CustomerCartFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_customer_cart, null);
        getActivity().setTitle("Cart");


        return v;
    }

}