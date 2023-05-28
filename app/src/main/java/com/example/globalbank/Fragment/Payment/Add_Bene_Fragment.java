package com.example.globalbank.Fragment.Payment;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.globalbank.R;
import com.example.globalbank.database.local.BeneModel;
import com.example.globalbank.database.local.RoomDB;


public class Add_Bene_Fragment extends Fragment {

    private Button btn_validate;
    private EditText ed_acc_num , ed_full_name;
    private RoomDB db;
    public Add_Bene_Fragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_add__bene_, container, false);

        btn_validate = root.findViewById(R.id.btn_validate);
        ed_acc_num = root.findViewById(R.id.ed_acc_num);
        ed_full_name = root.findViewById(R.id.ed_full_name);

        db = RoomDB.getInstance(getContext());

        Bundle b = this.getArguments();
        if (b != null) {
            ed_full_name.setText(b.getString("name_Payment"));
            ed_acc_num.setText(b.getString("rib_Payment"));
        }
        btn_validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String full_name = ed_full_name.getText().toString();
                String acc_num = ed_acc_num.getText().toString();
                add_bene(full_name , acc_num);

                Bundle bundle = new Bundle();
                bundle.putString("name_Credited" ,full_name );
                bundle.putString("RIB_Credited" , acc_num);
                bundle.putBoolean("true" , true);
                Fragment fragment = new Payment_Home_Fragment();
                fragment.setArguments(bundle);
                FragmentTransaction fn = getActivity().getSupportFragmentManager().beginTransaction();
                fn.replace(R.id.fragment_payment,fragment).commit();
            }
        });
        backPressedDispatcher();
        return root;
    }
    private void add_bene(String full_name , String RIB){
        BeneModel model = new BeneModel(full_name,RIB);
        db.mainDao().insert(model);
    }
    private void backPressedDispatcher(){
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Navigate back to Fragment 1
                Fragment fragment1 = new Payment_Home_Fragment();
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_payment, fragment1).commit();
            }
        });
    }
}