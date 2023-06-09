package com.example.globalbank.Fragment.Payment;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.globalbank.R;
import com.example.globalbank.adapter.Adapter_List_Bene;
import com.example.globalbank.database.local.BeneModel;
import com.example.globalbank.database.local.RoomDB;

import java.util.ArrayList;


public class Choose_BENE extends Fragment {

    private ArrayList<BeneModel> items ;
    private RoomDB db ;
    private Adapter_List_Bene adapter;
    public Choose_BENE() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_choose__b_e_n_e, container, false);

        ListView listView = (ListView) root.findViewById(R.id.lst_bene);
        db = RoomDB.getInstance(getContext());
        items=new ArrayList<>();
        items= (ArrayList<BeneModel>) db.mainDao().getAll();
        adapter=new Adapter_List_Bene(getContext(),items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                BeneModel model = items.get(i);

                Bundle bundle = new Bundle();
                bundle.putString("name_Credited" , model.getFull_name());
                bundle.putString("RIB_Credited" , model.getRIB());
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