package com.example.globalbank.Fragment.Payment;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.globalbank.Model.User;
import com.example.globalbank.Model.UserManager;
import com.example.globalbank.R;
import com.example.globalbank.activity.Payment;


public class Payment_Home_Fragment extends Fragment {

    private Button btn_Choose_Bene,btn_Add_Bene , btn_manage_bene , btn_next;
    private TextView txt_name_credi , txt_num_Credi , txt_p_rib , txt_p_name , txt_p_balance;
    EditText et_amount , et_reason;
    private boolean layout_visib = false;

    private int id;
    private String name_Credited, RIB_Credited , s_balance;


    public Payment_Home_Fragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_payment__home_, container, false);
        btn_Choose_Bene = root.findViewById(R.id.btn_choose_bene);
        btn_Add_Bene = root.findViewById(R.id.btn_Add_Bene);
        LinearLayout layout = root.findViewById(R.id.layout_Credited);
        txt_name_credi = root.findViewById(R.id.txt_name_credi);
        txt_num_Credi = root.findViewById(R.id.txt_num_credi);
        txt_p_balance = root.findViewById(R.id.txt_p_balance);
        txt_p_name = root.findViewById(R.id.txt_p_name);
        txt_p_rib = root.findViewById(R.id.txt_p_rib);
        btn_manage_bene = root.findViewById(R.id.btn_manage_bene);
        btn_next = root.findViewById(R.id.btn_next);
        et_amount = root.findViewById(R.id.et_amount);
        et_reason = root.findViewById(R.id.et_reason);





        User user = UserManager.getInstance().getUser();
        txt_p_balance.setText(String.valueOf(user.getBalance()));
        txt_p_name.setText(user.getSname());
        txt_p_rib.setText(user.getRibNumber());


        Bundle bundle = this.getArguments();
        if (bundle != null) {

            name_Credited = bundle.getString("name_Credited");
            RIB_Credited = bundle.getString("RIB_Credited");
            layout_visib = bundle.getBoolean("true");
        }



        if (layout_visib == false){
            layout.setVisibility(View.GONE);
            btn_next.setEnabled(false);

        }else {
            layout.setVisibility(View.VISIBLE);
            btn_next.setEnabled(true);

        }

        txt_name_credi.setText(name_Credited);
        txt_num_Credi.setText(RIB_Credited);


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float amount = Float.valueOf(et_amount.getText().toString());
                String reason = et_reason.getText().toString();
                float balance = user.getBalance();

                if (amount >= 1){

                    if (amount < balance){
                        Bundle bundle = new Bundle();
                        bundle.putString("name_C" , name_Credited);
                        bundle.putString("RIB_C" , RIB_Credited);
                        bundle.putString("amount" , String.valueOf(amount));
                        bundle.putString("reason" , reason);

                        Fragment selectedFragment = new Next_fragment();
                        selectedFragment.setArguments(bundle);
                        getParentFragmentManager().beginTransaction().replace(R.id.fragment_payment, selectedFragment).commit();


                    }else {
                        et_amount.setError("insufficient account balance");
                        et_amount.requestFocus();
                        Toast.makeText(getContext() , "insufficient account balance" , Toast.LENGTH_LONG).show();
                    }



                }else {
                    et_amount.setError("Minimum amount per transaction : 1");
                    et_amount.requestFocus();
                }






            }
        });
        btn_manage_bene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment selectedFragment = new Manage_List_Bene();
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_payment, selectedFragment).commit();

            }
        });
        btn_Choose_Bene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment selectedFragment = new Choose_BENE();
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_payment, selectedFragment).commit();
            }
        });

        btn_Add_Bene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment selectedFragment = new Add_Bene_Fragment();
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_payment, selectedFragment).commit();

            }
        });


        return root;
    }


}