package com.example.globalbank.Fragment.mainActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.example.globalbank.activity.Payment;
import com.example.globalbank.R;
import com.google.zxing.Result;

import org.json.JSONException;
import org.json.JSONObject;


public class ScanFragment extends Fragment {

    private CodeScanner mCodeScanner;

    JSONObject json2;
    String type , name , rib;
    public ScanFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_scan, container, false);

        if (ContextCompat.checkSelfPermission(getContext() , Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity() , new String[]{Manifest.permission.CAMERA}, 123);
        }
        final Activity activity = getActivity();
        CodeScannerView scannerView = root.findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(activity, scannerView);


        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                activity.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        Toast.makeText(activity, result.getText(), Toast.LENGTH_SHORT).show();

                        String data = result.getText();

                        try {

                            json2 = new JSONObject(data);
                            if (json2.has("type")&& json2.has("name") && json2.has("rib")){
                                type = json2.getString("type");
                                name = json2.getString("name");
                                rib = json2.getString("rib");

                                if (type.equals("add_bene")){

                                    Intent intent = new Intent(getActivity(), Payment.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("fragmentTag", "Add_Bene_Fragment");
                                    bundle.putString("name", name);
                                    bundle.putString("rib", rib);

                                    intent.putExtras(bundle);
                                    startActivity(intent);

                                }
                            }






                        } catch (JSONException e) {
                            e.printStackTrace();
                            // Handle the exception here
                        }








                    }

                });
            }
        });

        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });


        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    public void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }
    public void toastMessage(String message){
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 123) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), R.string.camera_permission_granted, Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(getContext(), R.string.camera_permission_denied, Toast.LENGTH_LONG).show();
            }
        }
    }



}