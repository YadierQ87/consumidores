package com.solprob.yadierq87.consumidores.pantallas_mensajes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.solprob.yadierq87.consumidores.Pantalla_inicio_sesion;
import com.solprob.yadierq87.consumidores.R;

public class Msg_restablecer_contrasenna extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_restablecer_contrasenna);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void onClickIntentPantallaInicio(View v) {
        finish();
        startActivityForResult(new Intent(this, Pantalla_inicio_sesion.class),1);
    }

}
