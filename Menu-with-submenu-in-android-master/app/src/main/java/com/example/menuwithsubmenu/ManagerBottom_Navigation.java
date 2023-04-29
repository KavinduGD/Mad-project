package com.example.menuwithsubmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.menuwithsubmenu.ManagerMenus.ManagerHomeFragment;
import com.example.menuwithsubmenu.ManagerMenus.ManagerOrderFragment;
import com.example.menuwithsubmenu.ManagerMenus.ManagerPastOrderFragment;
import com.example.menuwithsubmenu.ManagerMenus.ManagerProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ManagerBottom_Navigation extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_bottom_navigation);
        BottomNavigationView navigationView = findViewById(R.id.chef_bottom_navigation);
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container,new ManagerHomeFragment());
        transaction.commit();

        navigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.chefHome:
                fragment=new ManagerHomeFragment();
                break;
            case R.id.PendingOrders:
                fragment=new ManagerOrderFragment();
                break;
            case R.id.Orders:
                fragment=new ManagerPastOrderFragment();
                break;
            case R.id.chefProfile:
                fragment=new ManagerProfileFragment();
                break;
        }
        return loadcheffragment(fragment);
    }

    private boolean loadcheffragment(Fragment fragment) {

        if (fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,fragment).commit();
            return true;
        }
        return false;
    }
}