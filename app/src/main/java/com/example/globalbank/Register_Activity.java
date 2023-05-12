package com.example.globalbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.globalbank.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Register_Activity extends AppCompatActivity {

    private EditText etRegEmail, etRegPassword,etname;
    private Button btnRegister;
    private TextView btnlogin;
    private FirebaseAuth mAuth;
    private FirebaseDatabase db = FirebaseDatabase.getInstance("https://global-bank-35cd5-default-rtdb.europe-west1.firebasedatabase.app/");
    private DatabaseReference root = db.getReference().child("Users");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();



        etRegEmail=findViewById(R.id.edtxt_register_email);
        etRegPassword=findViewById(R.id.edtxt_register_password);

        etname=findViewById(R.id.edtxn_regester_name);
        btnRegister=findViewById(R.id.btn_register);
        btnlogin=findViewById(R.id.tnx_already_acc);

        mAuth=FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(view -> {
            //createuser();
        });
        btnlogin.setOnClickListener(view -> {
            switchToLogin();
        });

    }
/*
    private void createuser(){
        String sEmail = etRegEmail.getText().toString();
        String sPassword = etRegPassword.getText().toString();

        String sName = etname.getText().toString();


        if (TextUtils.isEmpty(sEmail)){
            etRegEmail.setError("Email cannot be empty");
            etRegEmail.requestFocus();
        }else if(TextUtils.isEmpty(sPassword)) {
            etRegPassword.setError("password cannot be empty");
            etRegPassword.requestFocus();
        }else if (TextUtils.isEmpty(sName)){
            etname.setError("prénom cannot be empty");
            etname.requestFocus();


        }else {
            mAuth.createUserWithEmailAndPassword(sEmail,sPassword)
                    .addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        String UserID = mAuth.getCurrentUser().getUid();
                        User user = new User(sName,sEmail,sPassword);




                        root.child(UserID).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                              MyToast("User registered successfully");
                                switchToLogin();
                            }
                        });


                    }else{
                        MyToast("Registration Error : "+task.getException().getMessage());
                    }
                }
            });
        }


    }

 */


    private void MyToast(String text) {
        Toast.makeText(Register_Activity.this, text, Toast.LENGTH_SHORT).show();
    }

    private void switchToLogin(){
        startActivity(new Intent(Register_Activity.this, Login_Activity.class));
        finish();
    }

}