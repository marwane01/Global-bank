package com.example.globalbank.Fragment.mainActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.ThemedSpinnerAdapter;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.provider.SyncStateContract;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.globalbank.Payment;
import com.example.globalbank.R;
import com.google.android.material.navigation.NavigationView;


public class HomeFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {


    public HomeFragment() {
        // Required empty public constructor
    }

    int RIB;
    String f_name, l_name;
    float balance;

    ListView myList;
    String listss[] = {"one", "TWO", "one", "TWO", "one", "TWO", "one", "TWO", "one", "TWO", "one", "TWO", "one", "TWO",
            "one", "TWO", "one", "TWO", "one", "TWO", "one", "TWO",
            "one", "TWO",};

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private Toolbar toolbar;

    private ImageButton btn_send;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        myList = root.findViewById(R.id.listview);
        myList.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, listss));


        toolbar = root.findViewById(R.id.toolbar);
        drawer = root.findViewById(R.id.drawer_layout);
        navigationView = root.findViewById(R.id.nav_view);
        btn_send = root.findViewById(R.id.btn_send);


        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), Payment.class));
            }
        });


        return root;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

}

