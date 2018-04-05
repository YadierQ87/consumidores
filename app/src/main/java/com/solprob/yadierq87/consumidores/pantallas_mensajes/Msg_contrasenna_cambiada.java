package com.solprob.yadierq87.consumidores.pantallas_mensajes;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.solprob.yadierq87.consumidores.Pantalla_inicio_sesion;
import com.solprob.yadierq87.consumidores.R;

public class Msg_contrasenna_cambiada extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_msg_contrasenna_cambiada);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void onClickIntentPantallaInicio(View v) {
        finish();
        startActivityForResult(new Intent(this, Pantalla_inicio_sesion.class),1);
    }

}
