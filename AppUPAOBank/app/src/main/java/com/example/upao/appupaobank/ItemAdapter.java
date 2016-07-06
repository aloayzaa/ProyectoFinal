package com.example.upao.appupaobank;

/**
 * Created by UPAO on 08/06/2016.
 */
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by HAMP on 7/06/2016.
 */
public class ItemAdapter extends BaseAdapter
{

    private ArrayList<HashMap<String,String>> datos;

    public ItemAdapter(ArrayList<HashMap<String, String>> datos) {
        this.datos = datos;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datos.size();
    }

    @Override
    public Object getItem(int position) {
        return datos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ItemView view;
        if(convertView==null) //NO existe, creamos uno
            view = new ItemView(parent.getContext());
        else                  //Existe, reutilizamos
            view = (ItemView) convertView;

        view.setItem((HashMap<String,String>)datos.get(position));
        return view;
    }
}
