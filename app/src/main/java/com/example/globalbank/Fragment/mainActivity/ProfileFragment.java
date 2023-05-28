package com.example.globalbank.Fragment.mainActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.globalbank.Fragment.Payment.Next_fragment;
import com.example.globalbank.Fragment.Payment.Payment_Home_Fragment;
import com.example.globalbank.Model.User;
import com.example.globalbank.Model.UserManager;
import com.example.globalbank.R;
import com.example.globalbank.activity.Login_Activity;
import com.example.globalbank.activity.MainActivity;
import com.google.firebase.auth.FirebaseAuth;


public class ProfileFragment extends Fragment {
    ImageView btn_share ,btn_qrcode;
    TextView txt_profile_email , txt_profile_phone , txt_profile_address;
    Button btn_logout;
    public ProfileFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        btn_share = root.findViewById(R.id.btn_share);
        btn_qrcode = root.findViewById(R.id.btn_qrcode);
        btn_logout = root.findViewById(R.id.btn_logout);
        txt_profile_email = root.findViewById(R.id.txt_profile_email);
        txt_profile_phone = root.findViewById(R.id.txt_profile_phone_n);
        txt_profile_address = root.findViewById(R.id.txt_profile_address);

        User user = UserManager.getInstance().getUser();
        txt_profile_email.setText(user.getEmail());

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutUser();
            }
        });
        btn_qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment selectedFragment = new QRCodeGenerator();
                getParentFragmentManager().beginTransaction().replace(R.id.flFragment, selectedFragment).commit();

            }
        });

        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = UserManager.getInstance().getUser();
                String s_RIB = user.getRibNumber();
                setBtn_share(s_RIB);

            }
        });
        backPressedDispatcher();
        return root;
    }

    public void setBtn_share(String text){
        Intent i = new Intent();
        i.setAction(Intent.ACTION_SEND);
        i.putExtra(Intent.EXTRA_TEXT , text);
        i.setType("text/plain");
        startActivity(i);
    }
    private void backPressedDispatcher(){
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Navigate back to Fragment 1
                Fragment fragment1 = new HomeFragment();
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_payment, fragment1).commit();
            }
        });

    }

    private void logoutUser() {

        startActivity(new Intent(getContext(), Login_Activity.class));
        getActivity().finish();

    }

}