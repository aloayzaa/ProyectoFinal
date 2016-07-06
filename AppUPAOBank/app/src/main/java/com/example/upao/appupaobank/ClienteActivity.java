package com.example.upao.appupaobank;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;


public class ClienteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
    }

    public void onClickConsultar(View view) {
        // Controles
        EditText etCodigo = (EditText) findViewById(R.id.cliente_et_codigo);
        TextView tvResultado = (TextView) findViewById(R.id.cliente_tv_resultado);
        try {
            // Dato
            String codigo = etCodigo.getText().toString();
            // Lanzar consulta
            String url = "ConsultarCliente.php?codigo=" + codigo;
            String jsonResult = Util.execJsonGetRequest(url);
            // Procesar el resultado
            JSONObject object = new JSONObject(jsonResult);
            String estado = object.getString("estado");
            if (estado.equals("0")) {
                throw new Exception("Dato incorrecto.");
            }
            String resultado = "Código: " + object.getString("chr_cliecodigo")
                    + "\nPaterno: " + object.getString("vch_cliepaterno")
                    + "\nMaterno: " + object.getString("vch_cliematerno")
                    + "\nNombre: " + object.getString("vch_clienombre");
            tvResultado.setText(resultado);
        } catch (Exception e) {
            tvResultado.setText(e.getMessage());
        }
    }

    public void onClickRetornar(View view) {
        // TODO
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        this.finish();
    }

}