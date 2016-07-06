package com.example.upao.appupaobank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

public class RegistroCliente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_cliente);
    }

    public void onClickAceptar(View view)
    {
        //Controles
        EditText etApPaterno = (EditText)findViewById(R.id.etApPaterno);
        EditText etApMaterno = (EditText)findViewById(R.id.etApMaterno);
        EditText etNombres = (EditText)findViewById(R.id.etNombres);
        EditText etDni = (EditText)findViewById(R.id.etDni);
        EditText etCorreo = (EditText)findViewById(R.id.etCorreo);
        TextView tvResultado = (TextView) findViewById(R.id.tvResultado);

        String ap_paterno = etApPaterno.getText().toString();
        String ap_materno= etApMaterno.getText().toString();
        String nombres= etNombres.getText().toString();
        String dni= etDni.getText().toString();
        String correo= etCorreo.getText().toString();


        try{
            //validar datos vacios
            if (ap_paterno.equals("") || ap_materno.equals("") || nombres.equals("") || dni.equals("") || correo.equals("")) {
                Toast.makeText(this, "Ha dejado campos vacios",
                        Toast.LENGTH_LONG).show();
            }else{
                //Util.URL_APP
                String url =  "RegistrarCliente.php?ap_paterno="
                        +ap_paterno+"&ap_materno="+ap_materno+"&nombres="+nombres+"&dni="+dni+"&correo="+correo;
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
                    etApPaterno.setText("");
                    etApMaterno.setText("");
                    etNombres.setText("");
                    etDni.setText("");
                    etCorreo.setText("");
                    etApPaterno.requestFocus();
                    tvResultado.setText(msje);
                }
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
