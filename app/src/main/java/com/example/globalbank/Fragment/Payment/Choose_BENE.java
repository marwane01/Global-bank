package com.example.globalbank.Fragment.Payment;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.globalbank.R;


public class Choose_BENE extends Fragment {


    public Choose_BENE() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_choose__b_e_n_e, container, false);

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Navigate back to Fragment 1
                Fragment fragment1 = new Payment_Home_Fragment();
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_payment, fragment1).commit();
            }
        });
        return root;
    }


}