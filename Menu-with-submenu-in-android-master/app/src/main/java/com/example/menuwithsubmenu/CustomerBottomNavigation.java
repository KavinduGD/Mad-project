package com.example.menuwithsubmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.menuwithsubmenu.CustomerMenus.CustomerCartFragment;
import com.example.menuwithsubmenu.CustomerMenus.CustomerHomeFragment;
import com.example.menuwithsubmenu.CustomerMenus.CustomerOrdersFragment;
import com.example.menuwithsubmenu.CustomerMenus.CustomerProfileFragment;
import com.example.menuwithsubmenu.CustomerMenus.CustomerTrackFragment;
import com.example.menuwithsubmenu.ManagerMenus.ManagerHomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CustomerBottomNavigation extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_bottom_navigation);
        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);

        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container1,new CustomerHomeFragment());
        transaction.commit();

        navigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.cust_Home:
                fragment=new CustomerHomeFragment();
                break;
        }
        switch (item.getItemId()){
            case R.id.cart:
                fragment=new CustomerCartFragment();
                break;
        }
        switch (item.getItemId()){
            case R.id.cust_profile:
                fragment=new CustomerProfileFragment();
                break;
        }
        switch (item.getItemId()){
            case R.id.Cust_order:
                fragment=new CustomerOrdersFragment();
                break;
        }
        switch (item.getItemId()){
            case R.id.track:
                fragment=new CustomerTrackFragment();
                break;
        }
        return loadcheffragment(fragment);

    }

    private boolean loadcheffragment(Fragment fragment) {

        if(fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container1,fragment).commit();
            return true;
        }
        return false;
    }
}