package com.solprob.yadierq87.consumidores.pantallas_mensajes;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.solprob.yadierq87.consumidores.R;

public class Msg_pt9_promocion_lanzamiento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_pantalla_promocion_lanzamiento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    // go to pantalla 10 Programar Consumo Diario
    public void onclickProgramarConsumoDiario(View v){
	    finish();
        startActivityForResult(new Intent(this, Msg_pt10_programar_consumo_diario.class), 1);
    }

}
