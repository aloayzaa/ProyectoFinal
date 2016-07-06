package com.example.upao.appupaobank;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MovimientosActivity extends AppCompatActivity
{

    // JSON Node names
    private static final String TAG_DATE = "dtt_movifecha";
    private static final String TAG_TYPE = "vch_tipodescripcion";
    private static final String TAG_AMOUNT = "dec_moviimporte";

    private static final String TAG_CLIENTE = "nombres";
    private static final String TAG_SALDO = "dec_cuensaldo";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimientos);

    }

    public void onClickConsultar(View view)
    {
        //Controles
        EditText etCuenta = (EditText) findViewById(R.id.movimientos_et_cuenta);
        TextView tvResultado = (TextView) findViewById(R.id.movimientos_tv_resultado);
        ListView lvDatos = (ListView) findViewById(R.id.movimientos_lv_lista);

        TextView tvcliente = (TextView)findViewById(R.id.tvCliente);
        TextView tvcuenta = (TextView)findViewById(R.id.tvCuenta);
        TextView tvsaldo = (TextView)findViewById(R.id.tvSaldo);

        try{
            //Dato
            String cuenta = etCuenta.getText().toString();
            //Lanzar consulta
            //Util.URL_APP +
            String url =  "ConsultarMovimientos.php?cuenta="
                    +cuenta;
            String jsonResult = Util.execJsonGetRequest(url);
            if(jsonResult.isEmpty())
            {
                tvcliente.setText("");
                tvcuenta.setText("");
                tvsaldo.setText("");
                tvResultado.setText("");
                ArrayList<HashMap<String,String>> blanco =
                        new ArrayList<HashMap<String, String>>();
                lvDatos.setAdapter(new ItemAdapter(blanco));
                throw new Exception("Cuenta no existe.");
            }
            //Procesar el Resultado

            JSONObject json = new JSONObject(jsonResult);
            //Obtener el elemento que contiene los movimientos
            JSONArray jArray = json.getJSONArray("movimientos");
            //Hasmap de ListView
            ArrayList<HashMap<String,String>> movimientos =
                    new ArrayList<HashMap<String, String>>();
            //Recorrer the Array
            for(int i=0; i< jArray.length(); i++)
            {
                HashMap<String,String> map = new HashMap<String,String>();
                JSONObject e = jArray.getJSONObject(i);

                map.put(TAG_DATE, e.getString(TAG_DATE));
                map.put(TAG_TYPE, e.getString(TAG_TYPE));
                map.put(TAG_AMOUNT,e.getString(TAG_AMOUNT));

                tvcliente.setText("Cliente: " + e.getString(TAG_CLIENTE));
                tvcuenta.setText("Cuenta: " + etCuenta.getText());
                tvsaldo.setText("Saldo: "+e.getString(TAG_SALDO));

                movimientos.add(map);
            }
            //Adaptando al ListView
            lvDatos.setAdapter(new ItemAdapter(movimientos));

        } catch (Exception e)
        {
            tvResultado.setText(e.getMessage());
            //throw e;
        }
    }

    public void onCLickRetornar(View view)
    {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        this.finish();
    }

}
