package com.example.globalbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;


import com.example.globalbank.Fragment.mainActivity.CardFragment;
import com.example.globalbank.Fragment.mainActivity.HomeFragment;
import com.example.globalbank.Fragment.mainActivity.ProfileFragment;
import com.example.globalbank.Fragment.mainActivity.ScanFragment;
import com.example.globalbank.Fragment.mainActivity.StatisticFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);





        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.statistic:
                    selectedFragment = new StatisticFragment();
                    break;
                case R.id.scan:
                    selectedFragment = new ScanFragment();
                    break;
                case R.id.card:
                    selectedFragment = new CardFragment();
                    break;
                case R.id.profile:
                    selectedFragment = new ProfileFragment();
                    break;
            }
            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, selectedFragment).commit();
            }
            return true;

        });

        Fragment initialFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, initialFragment).commit();



    }

    private void logoutUser() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(MainActivity.this, Login_Activity.class));
        finish();

    }
}