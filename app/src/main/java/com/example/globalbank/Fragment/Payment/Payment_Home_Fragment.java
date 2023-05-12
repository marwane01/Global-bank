package com.example.globalbank.Fragment.Payment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.globalbank.R;


public class Payment_Home_Fragment extends Fragment {

    Button btn_choose_bene;

    public Payment_Home_Fragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_payment__home_, container, false);
        btn_choose_bene = root.findViewById(R.id.btn_choose_bene);

        btn_choose_bene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment selectedFragment = new Choose_BENE();
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_payment, selectedFragment).commit();
            }
        });


        return root;
    }
}