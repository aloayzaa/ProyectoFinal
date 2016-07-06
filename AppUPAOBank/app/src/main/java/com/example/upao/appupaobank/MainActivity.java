package com.example.upao.appupaobank;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Condicion para iniciar con LoginActivity((s4login))
        TextView tvnombreusuario = (TextView) findViewById(R.id.tvUsuario);
        if(Util.nombre_empleado.toString().equalsIgnoreCase("")){

            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
            this.finish();
        }

        tvnombreusuario.setText("Usuario: "+Util.nombre_empleado);


        //Si se trata de Android Version 3 o superior
        //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        //StrictMode.setThreadPolicy(policy);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            System.out.println("*** My thread is now configured to allow connection");
        }
    }

    public void onClickClientes(View view)
    {
        Intent intent = new Intent(getApplicationContext(),ClienteActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void onClickMovimientos(View view)
    {
        Intent intent = new Intent(getApplicationContext(),MovimientosActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void onClickSalir(View view)
    {
        Util.nombre_empleado="";
        Util.codigo_empleado="";
        this.finish();
    }

    public void onClickRetiro(View view)
    {
        Intent intent = new Intent(getApplicationContext(),RetiroActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void onClickDeposito(View view)
    {
        Intent intent = new Intent(getApplicationContext(),DepositoActivity.class);
        startActivity(intent);
        this.finish();
    }

}
