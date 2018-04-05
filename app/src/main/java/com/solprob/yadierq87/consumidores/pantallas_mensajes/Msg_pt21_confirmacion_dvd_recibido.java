package com.solprob.yadierq87.consumidores.pantallas_mensajes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.solprob.yadierq87.consumidores.R;
import com.solprob.yadierq87.consumidores.menu_parent_principal.Pt22_Alta_codigo_seguridad_dvd;

public class Msg_pt21_confirmacion_dvd_recibido extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfaz_veintiuno_admin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void onClickIntentpt22AltaCodigoSeguridad(View v){
        finish();
        startActivityForResult(new Intent(this, Pt22_Alta_codigo_seguridad_dvd.class), 1);
    }

    public void onClickBtnNOGenerarAvisoAdmin(View v){
        Toast toast = Toast.makeText(this.getApplicationContext(), "Se Genero un aviso al administrador", Toast.LENGTH_SHORT);
        toast.show();
    }

}
