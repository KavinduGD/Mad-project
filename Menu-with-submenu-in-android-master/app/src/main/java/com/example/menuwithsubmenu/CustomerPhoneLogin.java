package com.example.menuwithsubmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.hbb20.CountryCodePicker;

public class CustomerPhoneLogin extends AppCompatActivity {

    EditText num;
    Button sendotp,signinemail;
    TextView signup;
    CountryCodePicker cpp;
    FirebaseAuth Fauth;
    String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_phone_login);

        num = (EditText)findViewById(R.id.number1);
        sendotp = (Button)findViewById(R.id.otp1);
        cpp=(CountryCodePicker)findViewById(R.id.CountryCode1);
        signinemail=(Button)findViewById(R.id.btnEmail1);
        signup = (TextView)findViewById(R.id.acsignup1);

        Fauth = FirebaseAuth.getInstance();

        sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number=num.getText().toString().trim();
                String Phonenum = cpp.getSelectedCountryCodeWithPlus()+number;
                Intent b = new Intent(CustomerPhoneLogin.this,CustomerSendOTP.class);

                b.putExtra("Phonenumber",Phonenum);
                startActivity(b);
                finish();

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerPhoneLogin.this,CustomerSignIn.class));
                finish();
            }
        });
        signinemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerPhoneLogin.this,CustomerLogin.class));
                finish();
            }
        });
    }
}