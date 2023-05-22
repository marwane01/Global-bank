package com.example.globalbank.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.globalbank.Model.User;
import com.example.globalbank.R;
import com.example.globalbank.RIBgenerator;
import com.example.globalbank.database.online.DbOnline;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register_Activity extends AppCompatActivity {

    private EditText etRegEmail, etRegPassword,etname;
    private Button btnRegister;
    private TextView btnlogin;

    DbOnline db = new DbOnline();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //ActionBar actionBar = getSupportActionBar();
        //actionBar.hide();



        etRegEmail=findViewById(R.id.edtxt_register_email);
        etRegPassword=findViewById(R.id.edtxt_register_password);

        etname=findViewById(R.id.edtxn_regester_name);
        btnRegister=findViewById(R.id.btn_register);
        btnlogin=findViewById(R.id.tnx_already_acc);


        btnRegister.setOnClickListener(view -> {
            createUserByRIB();


        });
        btnlogin.setOnClickListener(view -> {
            switchToLogin();
        });

    }

    private void createUserByRIB() {
        String sEmail = etRegEmail.getText().toString();
        String sPassword = etRegPassword.getText().toString();
        String sName = etname.getText().toString();

        if (TextUtils.isEmpty(sEmail)) {
            etRegEmail.setError("Email cannot be empty");
            etRegEmail.requestFocus();
        } else if (TextUtils.isEmpty(sPassword)) {
            etRegPassword.setError("Password cannot be empty");
            etRegPassword.requestFocus();
        } else if (TextUtils.isEmpty(sName)) {
            etname.setError("Prénom cannot be empty");
            etname.requestFocus();
        } else {
            DbOnline db = new DbOnline();
            db.checkEmailExists(sEmail, new DbOnline.OnEmailCheckListener() {
                @Override
                public void onEmailCheckComplete(boolean emailAvailable) {
                    if (emailAvailable) {
                        // Email does not exist in the database
                        String RIB = RIBgenerator.generateRIB("150", "101");
                        db.CreateUserById(RIB, sEmail, sPassword, sName);

                        switchToLogin();
                    } else {
                        // Email already exists in the database
                        etRegEmail.setError("Email already exists");
                        etRegEmail.requestFocus();
                    }
                }
            });
        }
    }




    private void MyToast(String text) {
        Toast.makeText(Register_Activity.this, text, Toast.LENGTH_SHORT).show();
    }

    private void switchToLogin(){
        startActivity(new Intent(Register_Activity.this, Login_Activity.class));
        finish();
    }

}

