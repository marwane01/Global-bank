package com.example.globalbank.Fragment.mainActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.globalbank.Model.User;
import com.example.globalbank.Model.UserManager;
import com.example.globalbank.activity.Payment;
import com.example.globalbank.R;
import com.example.globalbank.database.online.DbOnline;
import com.google.android.material.navigation.NavigationView;


public class HomeFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener,
        DbOnline.OnUserDataUpdateListener  {


    public HomeFragment() {
        // Required empty public constructor
    }

    int RIB;
    String f_name, l_name;
    float balance;

    private ListView myList;

    private DbOnline dbOnline;
    String listss[] = {"one", "TWO", "one", "TWO", "one", "TWO", "one", "TWO", "one", "TWO", "one", "TWO", "one", "TWO",
            "one", "TWO", "one", "TWO", "one", "TWO", "one", "TWO",
            "one", "TWO",};

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    TextView txt_balance,txt_toolbar_title;

    private ImageButton btn_send,btn_more;


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
        btn_more = root.findViewById(R.id.btn_more);
        txt_balance = root.findViewById(R.id.txt_balance);
        txt_toolbar_title = root.findViewById(R.id.txtToolbarTitle);

        dbOnline = new DbOnline();
        dbOnline.listenForUserDataUpdates(this);


        User user = UserManager.getInstance().getUser();



        txt_toolbar_title.setText("Welcome " + user.getSname() +"\n" +user.getRibNumber() );
        txt_balance.setText(String.valueOf(user.getBalance()));

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


        btn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });


        return root;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }


    @Override
    public void onUserDataUpdated(User user) {
        txt_toolbar_title.setText("Welcome " + user.getSname() + "\n" + user.getRibNumber());
        txt_balance.setText(String.valueOf(user.getBalance()));
    }

    @Override
    public void onUserDataUpdateFailed(String error) {

    }
}

