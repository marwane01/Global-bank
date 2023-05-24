package com.example.globalbank.Fragment.Payment;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.globalbank.Model.User;
import com.example.globalbank.Model.UserManager;
import com.example.globalbank.R;
import com.example.globalbank.database.online.DbOnline;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class Next_fragment extends Fragment {


    TextView txt_name_from , txt_rib_from,txt_name_to,txt_rib_to,txt_amount,txt_note,btn_next_cancel;
    Button btn_next_confirmer;
    String reason ;
    DbOnline dbOnline;

    public Next_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_next_fragment, container, false);

        txt_name_from = root.findViewById(R.id.txt_name_from);
        txt_rib_from = root.findViewById(R.id.txt_rib_from);
        txt_name_to = root.findViewById(R.id.txt_name_to);
        txt_rib_to = root.findViewById(R.id.txt_rib_to);
        txt_amount = root.findViewById(R.id.txt_amount);
        txt_note = root.findViewById(R.id.txt_note);
        btn_next_cancel = root.findViewById(R.id.btn_next_cancel);
        btn_next_confirmer = root.findViewById(R.id.btn_next_confirmer);
        dbOnline = new DbOnline(getContext());

        User user = UserManager.getInstance().getUser();

        txt_name_from.setText(user.getSname());
        txt_rib_from.setText(user.getRibNumber());

        Bundle bundle = this.getArguments();
        if (bundle != null) {

            txt_name_to.setText(bundle.getString("name_C"));
            txt_rib_to.setText(bundle.getString("RIB_C"));
            txt_amount.setText(bundle.getString("amount"));
            txt_note.setText(bundle.getString("reason"));
            reason = bundle.getString("reason");
        }

        btn_next_confirmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String toRIB = txt_rib_to.getText().toString();
                String fromRIB = txt_rib_from.getText().toString();
                float amount = Float.valueOf(txt_amount.getText().toString());
                String currentDate = getCurrentDate();
                String name_sender =txt_name_from.getText().toString();
                String name_receiver = txt_name_to.getText().toString();
                dbOnline.updateBalances(fromRIB,name_sender, toRIB,name_receiver, amount, reason, currentDate, new DbOnline.OnTransactionListener() {
                    @Override
                    public void onTransactionSuccess() {
                        Toast.makeText(getContext(),"Transaction Success", Toast.LENGTH_SHORT).show();
                        Fragment fragment1 = new Payment_Home_Fragment();
                        getParentFragmentManager().beginTransaction().replace(R.id.fragment_payment, fragment1).commit();

                    }

                    @Override
                    public void onTransactionCancelled() {
                        Toast.makeText(getContext(),"Transaction failed", Toast.LENGTH_SHORT).show();
                        Fragment fragment1 = new Payment_Home_Fragment();
                        getParentFragmentManager().beginTransaction().replace(R.id.fragment_payment, fragment1).commit();


                    }
                });



            }
        });

        btn_next_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment1 = new Payment_Home_Fragment();
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_payment, fragment1).commit();

            }
        });




        backPressedDispatcher();
        return root;
    }

    private boolean performTransaction(String fromRIB, String toRIB, String amount) {




        return false;
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

    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date currentDate = new Date();
        return dateFormat.format(currentDate);
    }

}