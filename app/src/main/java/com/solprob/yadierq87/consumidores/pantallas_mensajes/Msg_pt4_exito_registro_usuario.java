package com.solprob.yadierq87.consumidores.pantallas_mensajes;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.solprob.yadierq87.consumidores.R;

public class Msg_pt4_exito_registro_usuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_msg_registro_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       }

       public void onClickSiguiente(View v){
           finish();
           startActivityForResult(new Intent(this, Msg_pt5_video_publicidad_nuevo.class), 1);
       }



}
