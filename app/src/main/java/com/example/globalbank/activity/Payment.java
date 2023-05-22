package com.example.globalbank.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.globalbank.Fragment.Payment.Add_Bene_Fragment;
import com.example.globalbank.Fragment.Payment.Payment_Home_Fragment;
import com.example.globalbank.R;

public class Payment extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);





        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String fragmentTag = bundle.getString("fragmentTag");
            String name = bundle.getString("name");
            String rib = bundle.getString("rib");
            if (fragmentTag != null) {
                if (fragmentTag.equals("Add_Bene_Fragment")) {

                    Bundle bndl = new Bundle();
                    bundle.putString("name_Payment" ,name );
                    bundle.putString("rib_Payment" , rib);
                    Fragment fragment = new Add_Bene_Fragment();
                    fragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_payment, fragment)
                            .commit();



                }
            }
        }else {
            Fragment initialFragment = new Payment_Home_Fragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_payment, initialFragment).commit();

        }


    }
}