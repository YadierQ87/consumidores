package com.solprob.yadierq87.consumidores.pantallas_mensajes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.solprob.yadierq87.consumidores.Pantalla_inicio_sesion;
import com.solprob.yadierq87.consumidores.R;

public class Msg_pt18_confirmar_pago_pim extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_pago_pim);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    public void onClickGo19InicioSesionAdmin(View v){
        finish();
        startActivityForResult(new Intent(this,Pantalla_inicio_sesion.class), 1);
    }

    public void onClickGo20ConfirmarDiarioRecibido(View v){
        finish();
        startActivityForResult(new Intent(this,Msg_pt20_Confirmacion_diario_recibido.class), 1);
    }


}
