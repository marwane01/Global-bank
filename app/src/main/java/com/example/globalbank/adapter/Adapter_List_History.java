package com.example.globalbank.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.globalbank.Model.Transaction_H;
import com.example.globalbank.Model.User;
import com.example.globalbank.Model.UserManager;
import com.example.globalbank.R;

import java.util.ArrayList;

public class Adapter_List_History extends BaseAdapter {
    private Context context;
    private ArrayList<Transaction_H> listitems;
    private LayoutInflater inflater;


    public Adapter_List_History(Context c, ArrayList<Transaction_H> listitems){
        context = c;
        this.listitems=listitems;
    }


    @Override
    public int getCount() {
        return listitems.size();
    }

    @Override
    public Transaction_H getItem(int position) {
        return listitems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater==null){
            inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView==null){
            convertView=inflater.inflate(R.layout.adapter_list_history,null);
        }
        TextView txt_list_name = convertView.findViewById(R.id.list_item_name);
        TextView txt_list_num = convertView.findViewById(R.id.list_item_amount);
        try {
            Transaction_H transaction = getItem(position);
            String receiverName = transaction.getName_receiver();
            String senderName = transaction.getName_sender();
            String amount = String.valueOf(transaction.getAmount());
            String formattedAmount;

            User user = UserManager.getInstance().getUser();

            if (transaction.getReceiver_rib().equals(user.getRibNumber())) {
                // for the anount
                formattedAmount = "+ " + amount;
                txt_list_num.setText(formattedAmount);
                txt_list_num.setTextColor(Color.GREEN);

                //about the text
                String message = "You have received money from " + senderName + ":";
                txt_list_name.setText(message);

            } else {
                formattedAmount = "- " + amount;
                txt_list_num.setText(formattedAmount);
                txt_list_num.setTextColor(Color.RED);

                String message = "You have sent money to " + receiverName + ":";
                txt_list_name.setText(message);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return convertView;
    }


}
