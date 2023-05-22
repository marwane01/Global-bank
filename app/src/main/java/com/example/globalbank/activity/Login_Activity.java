package com.example.globalbank.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.globalbank.Model.User;
import com.example.globalbank.R;
import com.example.globalbank.database.online.DbOnline;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_Activity extends AppCompatActivity {
    EditText etLoginEmail , etLoginPassword;
    TextView textView;
    Button bnt_login;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //ActionBar actionBar = getSupportActionBar();
        //actionBar.hide();

        mAuth = FirebaseAuth.getInstance();
        etLoginEmail = findViewById(R.id.edtxt_login_email);
        etLoginPassword = findViewById(R.id.edtxt_login_password);
        bnt_login= findViewById(R.id.btn_login);
        textView=findViewById(R.id.txt_register);

        //switch to register
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToRegister();
            }
        });

        //login
        bnt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

    }


    private void loginUser(){
        String semail = etLoginEmail.getText().toString();
        String spassword = etLoginPassword.getText().toString();
        if (TextUtils.isEmpty(semail)){
            etLoginEmail.setError("Email can't be empty");
            etLoginEmail.requestFocus();
        }else if(TextUtils.isEmpty(spassword)){
            etLoginPassword.setError("password can't be empty");
            etLoginPassword.requestFocus();
        }else {

            DbOnline db = new DbOnline(this);
            db.LoginUser(semail, spassword,new DbOnline.OnLoginListener() {
                @Override
                public void onLoginSuccess(Boolean passwordCorrect) {
                    if (passwordCorrect == true){
                        showMainaAtivity();

                    }
                }
            });
        }
    }

    private void MyToast(String text) {
        Toast.makeText(Login_Activity.this, text, Toast.LENGTH_SHORT).show();
    }

    private void showMainaAtivity(){
        startActivity(new Intent(Login_Activity.this, MainActivity.class));
        finish();
    }
    private void switchToRegister(){
        startActivity(new Intent(Login_Activity.this, Register_Activity.class));
        finish();
    }


}