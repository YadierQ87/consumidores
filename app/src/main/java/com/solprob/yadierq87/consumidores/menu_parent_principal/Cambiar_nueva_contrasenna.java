package com.solprob.yadierq87.consumidores.menu_parent_principal;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.solprob.yadierq87.consumidores.Pantalla_inicio_sesion;
import com.solprob.yadierq87.consumidores.R;
import com.solprob.yadierq87.consumidores.pantallas_mensajes.Msg_contrasenna_cambiada;

public class Cambiar_nueva_contrasenna extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_nueva_contrasenna);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void onClickIntentPantallaInicio(View v) {
        finish();
        startActivityForResult(new Intent(this, Pantalla_inicio_sesion.class),1);
    }

    public void onClickMsgNuevaContrasenna(View v) {
        finish();
        startActivityForResult(new Intent(this, Msg_contrasenna_cambiada.class),1);
    }

}
