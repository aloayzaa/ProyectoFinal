package com.example.upao.appupaobank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

public class DepositoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposito);


        TextView tvnombreusuario = (TextView) findViewById(R.id.tvNombreUsuario);
        if(Util.nombre_empleado.toString().equalsIgnoreCase("")){

            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
            this.finish();
        }
        tvnombreusuario.setText("Usuario: " + Util.nombre_empleado);



    }




    public void onClickAceptar(View view)
    {
        //Controles
        EditText etCuenta = (EditText) findViewById(R.id.textCuenta);
        TextView tvMonto = (TextView)findViewById(R.id.textMonto);
        TextView tvResultado = (TextView) findViewById(R.id.tvResultado);
        String cuenta = etCuenta.getText().toString();
        String monto= tvMonto.getText().toString();
        String empleado= Util.codigo_empleado;
        try{
            //Dato


            //Lanzar consulta
            //Util.URL_APP
            String url =  "RegistrarDeposito.php?cuenta="
                    +cuenta+"&importe="+monto+"&empleado="+empleado;
            String jsonResult = Util.execJsonGetRequest(url);
            if(jsonResult.isEmpty())
            {
                throw new Exception("Cuenta no existe.");
            }


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
                tvMonto.setText("");
                tvResultado.setText(msje);

            }

        } catch (Exception e)
        {
            tvResultado.setText("AQUI EL ERROR "+e.getMessage());

        }
    }
    public void onCLickRetornar(View view)
    {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        this.finish();
    }
}
