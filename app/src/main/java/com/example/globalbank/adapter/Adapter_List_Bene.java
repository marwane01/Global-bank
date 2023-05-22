package com.example.globalbank.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.globalbank.R;
import com.example.globalbank.database.local.BeneModel;
import com.example.globalbank.database.local.RoomDB;

import java.util.ArrayList;

public class Adapter_List_Bene extends BaseAdapter {
    private Context context;
    private ArrayList<BeneModel> listitems;
    private LayoutInflater inflater;


    public Adapter_List_Bene(Context c, ArrayList<BeneModel> listitems){
        context = c;
        this.listitems=listitems;
    }


    @Override
    public int getCount() {
        return listitems.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
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
            convertView=inflater.inflate(R.layout.adapter_list_bene,null);
        }

        RoomDB db = RoomDB.getInstance(convertView.getContext());

        TextView txt_list_name = convertView.findViewById(R.id.txt_list_name);
        TextView txt_list_num = convertView.findViewById(R.id.txt_list_num);

        ImageView image_bank_type = convertView.findViewById(R.id.image_bank_type);







        try {
            BeneModel QRmodel = listitems.get(position);
            txt_list_name.setText(QRmodel.getFull_name());
            txt_list_num.setText(QRmodel.getRIB());







        }catch (Exception e){


        }
        return convertView;
    }
}
