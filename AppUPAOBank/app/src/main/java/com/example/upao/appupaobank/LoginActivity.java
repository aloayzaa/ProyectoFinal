package com.example.upao.appupaobank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    public void onClickIngresar(View view)
    {
        //Controles
        EditText etUsuario = (EditText) findViewById(R.id.etUsuario);
        EditText etClave = (EditText) findViewById(R.id.etClave);

        TextView tvResultado = (TextView) findViewById(R.id.tvTitulo);

        try
        {
            //Dato
            String usuario = etUsuario.getText().toString();
            String clave = etClave.getText().toString();
            //Lanzar consulta
            //Util.URL_APP +
            String url =  "login.php?usuario=" + usuario +"&clave="+clave;
            String jsonResult = Util.execJsonGetRequest(url);

            //Procesar el resultado
            JSONObject object = new JSONObject(jsonResult);
            String estado = object.getString("estado");

            if(estado.equals("0"))
            {
                throw new Exception("Datos incorrectos.");
            }

            Util.codigo_empleado=object.getString("chr_emplcodigo");
            Util.nombre_empleado=object.getString("nombre");

            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            this.finish();
            //tvResultado.setText(object.getString("chr_emplcodigo"));

        }
        catch(Exception e)
        {
            tvResultado.setText("AQUI EL ERROR "+e.getMessage());
        }
    }

}
