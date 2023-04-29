package com.example.menuwithsubmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainPage extends AppCompatActivity {

    Button login,signin,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        login=(Button)findViewById(R.id.Login_bt);
       phone=(Button)findViewById(R.id.phone_btn);
        signin=(Button)findViewById(R.id.sign_google_btn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signemail = new Intent(MainPage.this,ChooseOne.class);
                signemail.putExtra("Home","Login");
                startActivity(signemail);
                finish();
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signphone = new Intent(MainPage.this,ChooseOne.class);
                signphone.putExtra("Home","SignIn");
                startActivity(signphone);
                finish();
            }
        });

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent phone = new Intent(MainPage.this,ChooseOne.class);
                phone.putExtra("Home","Phone");
                startActivity(phone);
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
    }
}