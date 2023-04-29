package com.example.menuwithsubmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Deleteuser extends AppCompatActivity {

    private FirebaseAuth authprofile;
    private FirebaseUser firebaseUser;
    private EditText editTextUserPwd;
    private TextView textViewAuthentivated;
    private ProgressBar progressBar;
    private String userPwd;
    private Button buttonAuthenticate,buttonDeleeteuser;
    private static final String TAG= "DeleteProfileActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deleteuser);

        getSupportActionBar().setTitle("Delete Your Profile");

        progressBar=findViewById(R.id.progressBar);
        editTextUserPwd=findViewById(R.id.editText_delete_user_pwd);
        textViewAuthentivated =findViewById(R.id.textView_delete_user_authenticated);
        buttonDeleeteuser=findViewById(R.id.button_delete_user);
        buttonAuthenticate=findViewById(R.id.button_delete_user_authenticate);

        buttonDeleeteuser.setEnabled((false));

        authprofile=FirebaseAuth.getInstance();
        firebaseUser=authprofile.getCurrentUser();

        if(firebaseUser.equals("")){
            Toast.makeText(Deleteuser.this,"Something went wreong! user details are not available at the movement",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(Deleteuser.this,MainPage.class);
            startActivity(intent);
            finish();
        }else{
           reAuthenticateUsesr(firebaseUser);

        }


    }

    private void reAuthenticateUsesr(FirebaseUser firebaseUser) {
        buttonAuthenticate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userPwd=editTextUserPwd.getText().toString();
                if(TextUtils.isEmpty(userPwd)){
                    Toast.makeText(Deleteuser.this,"password is needed",Toast.LENGTH_SHORT);
                    editTextUserPwd.setError("please enter current password to authonticate");
                    editTextUserPwd.requestFocus();
                }else{
                    progressBar.setVisibility(View.VISIBLE);

                    //reauthanticate user now

                    AuthCredential credential= EmailAuthProvider.getCredential(firebaseUser.getEmail(),userPwd );
                    firebaseUser.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                progressBar.setVisibility(View.GONE);

                                //dissavle editText for Password
                                editTextUserPwd.setEnabled(false);

                                //enable delete button.Dissable Authenticate Button
                                buttonAuthenticate.setEnabled(false);
                                buttonDeleeteuser.setEnabled(true);

                                //set textView to show User is authnticated
                                textViewAuthentivated.setText("Your are authanticated now tou"+"you can delete your profile now ");
                                Toast.makeText(Deleteuser.this,"password bean verified",Toast.LENGTH_SHORT).show();

                                //update color of change password Button
                                buttonDeleeteuser.setBackgroundTintList(ContextCompat.getColorStateList(Deleteuser.this,R.color.Red));

                                buttonDeleeteuser.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        showAlertDialog();
                                    }
                                });


                            }else{
                                try{
                                    throw  task.getException();
                                }catch(Exception e){
                                    Toast.makeText(Deleteuser.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            }
                            progressBar.setVisibility(View.GONE);

                        }
                    });
                }
            }
        });
    }


    private void showAlertDialog() {

        // setup the elaet builder
        AlertDialog.Builder builder= new AlertDialog.Builder(Deleteuser.this);
        builder.setTitle("Delete User and related data");
        builder.setMessage("Do you really want to delete the profile");

        //open Email Apps if user clicxks/taps continue
        builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               deleteUser(firebaseUser);

            }
        });
        //cancel button eka click kalama main page ekaakata aynna
        builder.setNegativeButton("Cansel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent=new Intent(Deleteuser.this,MainPage.class);
                startActivity(intent);
                finish();
            }
        });



        //create the alertDialog
        AlertDialog alertDialog=builder.create();

        //change the  button color of coninue
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.blueish));
            }
        });
        //showthe AlertBox
        alertDialog.show();

    }

    private void deleteUser(FirebaseUser firebaseUser) {
        firebaseUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    deleteUserData();
                    authprofile.signOut();
                    Toast.makeText(Deleteuser.this,"User has been deleted",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Deleteuser.this,MainPage.class);
                    startActivity(intent);
                    finish();
                }else{
                    try{
                        throw  task.getException();
                    }catch (Exception e){
                        Toast.makeText(Deleteuser.this,"e.getMessage",Toast.LENGTH_SHORT).show();
                    }
                }
                progressBar.setVisibility(View.GONE);
            }
        });

    }
// delete all the data of the user
    private void deleteUserData() {

        //real time
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Customer");
        databaseReference.child(firebaseUser.getUid()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d(TAG,"Onsucess : user Data");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, e.getMessage());
                Toast.makeText(Deleteuser.this,e.getMessage(),Toast.LENGTH_SHORT);
            }
        });
        DatabaseReference databaseReference1= FirebaseDatabase.getInstance().getReference("User");
        databaseReference1.child(firebaseUser.getUid()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d(TAG,"Onsucess : user Data");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, e.getMessage());
                Toast.makeText(Deleteuser.this,e.getMessage(),Toast.LENGTH_SHORT);
            }
        });


    }


}