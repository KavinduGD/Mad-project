package com.example.menuwithsubmenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class ChooseOne extends AppCompatActivity {

    Button Manager,Customer;
    Intent intent;
    String type;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_one);

        AnimationDrawable animationDrawable = new AnimationDrawable();
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.bg2),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img2),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img3),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img4),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img5),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img6),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img7),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img8),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.bg3),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img9),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img10),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img11),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img11),3000);

        animationDrawable.setOneShot(false);
        animationDrawable.setEnterFadeDuration(850);
        animationDrawable.setExitFadeDuration(1600);

        imageView=findViewById(R.id.imageView4);
        imageView.setBackgroundDrawable(animationDrawable);
        animationDrawable.start();

        intent = getIntent();
        type = intent.getStringExtra("Home").toString().trim();

        Manager = (Button)findViewById(R.id.chef);
        Customer = (Button)findViewById(R.id.customer);


        Manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type.equals("Login")){
                    Intent login  = new Intent(ChooseOne.this,ManagerLogin.class);
                    startActivity(login);
                    finish();
                }
                if(type.equals("SignIn")){
                    Intent sign = new Intent(ChooseOne.this,ManagerSignIn.class);
                    startActivity(sign);
                    finish();
                }
                if(type.equals("Phone")){
                    Intent phone = new Intent(ChooseOne.this,Manager_Phone_Login.class);
                    startActivity(phone);
                    finish();
                }

            }
        });

        Customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(type.equals("Login")){
                    Intent cuslogin  = new Intent(ChooseOne.this,CustomerLogin.class);
                    startActivity(cuslogin);
                    finish();
                }
                if(type.equals("SignIn")){
                    Intent custSign  = new Intent(ChooseOne.this,CustomerSignIn.class);
                    startActivity(custSign);
                    finish();
                }
                if(type.equals("Phone")){
                    Intent phone1 = new Intent(ChooseOne.this,CustomerPhoneLogin.class);
                    startActivity(phone1);
                    finish();
                }

            }
        });



    }
}