package com.example.upao.appupaobank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

public class RetiroActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retiro);
        TextView tvnombreusuario = (TextView) findViewById(R.id.tvUsuario);
        if(Util.nombre_empleado.toString().equalsIgnoreCase("")){

            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
            this.finish();
        }
        tvnombreusuario.setText("Usuario: " + Util.nombre_empleado);
    }

    public void onClickIngresar(View view)
    {
        //Controles
        EditText etCuenta = (EditText) findViewById(R.id.etCuenta);
        EditText etMonto = (EditText) findViewById(R.id.etMonto);
        EditText etClave = (EditText) findViewById(R.id.etClave);

        TextView tvResultado = (TextView) findViewById(R.id.tvResultado);
        //Dato
        String cuenta = etCuenta.getText().toString();
        String monto = etMonto.getText().toString();
        String usuario = Util.codigo_empleado;
        String clave = etClave.getText().toString();
        try
        {

            //Lanzar consulta
            //Util.URL_APP +
            String url =  "registrarRetiro.php?cuenta=" + cuenta +"&importe="+monto+"&clave="+clave+"&empleado="+usuario;
            String jsonResult = Util.execJsonGetRequest(url);

            //Procesar el resultado
            JSONObject object = new JSONObject(jsonResult);
            String estado = object.getString("estado");
            String msje = object.getString("message");
            if(estado.equals("0"))
            {
                throw new Exception(msje);

            }
            else
            {

                etCuenta.setText("");
                etMonto.setText("");
                etClave.setText("");
                tvResultado.setText(msje);
            }


        }
        catch(Exception e)
        {
            tvResultado.setText("AQUI EL ERROR "+e.getMessage());
            //tvResultado.setText("AQUI"+cuenta+monto+usuario+clave);
        }
    }
    public void onCLickRetornar(View view)
    {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        this.finish();
    }
}
