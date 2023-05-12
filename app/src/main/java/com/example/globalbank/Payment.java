package com.example.globalbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Button;

import com.example.globalbank.Fragment.Payment.Payment_Home_Fragment;

public class Payment extends AppCompatActivity {

    Button btn_choose_bene;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Fragment initialFragment = new Payment_Home_Fragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_payment, initialFragment).commit();

    }
}