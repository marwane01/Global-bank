package com.example.globalbank.Fragment.mainActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.globalbank.Model.User;
import com.example.globalbank.Model.UserManager;
import com.example.globalbank.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import org.json.JSONException;
import org.json.JSONObject;


public class QRCodeGenerator extends Fragment {
    ImageView img_QrCode;
    String name , RIB ;
    public QRCodeGenerator() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_q_rcode, container, false);
        img_QrCode = root.findViewById(R.id.img_QRCode);

        User user = UserManager.getInstance().getUser();
        name = user.getSname();
        RIB = user.getRibNumber();

        Bitmap qrCodeBitmap = generateQRCodeBitmap("add_bene", name, RIB);
        img_QrCode.setImageBitmap(qrCodeBitmap);

        backPressedDispatcher();
        return root;
    }


    public static Bitmap generateQRCodeBitmap(String type,String name, String rib) {
        try {
            JSONObject json = new JSONObject();
            json.put("type" , type);
            json.put("name", name);
            json.put("rib", rib);

            String jsonData = json.toString();

            BitMatrix bitMatrix = new MultiFormatWriter().encode(jsonData, BarcodeFormat.QR_CODE, 200, 200);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bitmap.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }

            return bitmap;
        } catch (WriterException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void backPressedDispatcher(){
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Navigate back to Fragment 1
                Fragment fragment1 = new ProfileFragment();
                getParentFragmentManager().beginTransaction().replace(R.id.flFragment, fragment1).commit();
            }
        });

    }

}