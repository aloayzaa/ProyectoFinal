package com.example.upao.appupaobank;

/**
 * Created by UPAO on 08/06/2016.
 */
import java.util.HashMap;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ItemView extends LinearLayout {
    private TextView tvFecha;
    private TextView tvTipo;
    private TextView tvImporte;

    public ItemView(Context context) {
        super(context);
        inflate(context, R.layout.layout_item, this);
        // Controles
        tvFecha = (TextView) findViewById(R.id.item_tv_fecha);
        tvTipo = (TextView) findViewById(R.id.item_tv_tipo);
        tvImporte = (TextView) findViewById(R.id.item_tv_importe);
    }

    public void setItem(HashMap<String, String> item) {

        tvFecha.setText(item.get("dtt_movifecha").toString());
        tvTipo.setText(item.get("vch_tipodescripcion").toString());
        tvImporte.setText(item.get("dec_moviimporte").toString());
    }

}

