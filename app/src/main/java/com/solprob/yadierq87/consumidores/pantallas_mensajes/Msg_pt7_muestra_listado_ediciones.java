package com.solprob.yadierq87.consumidores.pantallas_mensajes;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.solprob.yadierq87.consumidores.R;

public class Msg_pt7_muestra_listado_ediciones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_pantalla_listado_ediciones);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingBtnEdiciones);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lanzarIntentSiguientePantalla8();
            }
        });
    }

    public void lanzarIntentSiguientePantalla8(){
        finish();
        startActivityForResult(new Intent(this, Msg_pt8_terminos_edicion.class), 1);
    }

    public void onClickImagenPrimeraEdicion(View v){
        ImageView imagen = (ImageView)findViewById(R.id.imageViewCambiarEdicion);
        imagen.setImageResource(R.drawable.t1edicion);
        ClearButtonStyles();
        Button btnEdicion = (Button) findViewById(R.id.buttonedicion1);
        Drawable fondo = getResources().getDrawable(R.drawable.line_primarydark);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            btnEdicion.setBackground(fondo);
        }

    }

    public void onClickImagenSegundaEdicion(View v){
        ImageView imagen = (ImageView)findViewById(R.id.imageViewCambiarEdicion);
        imagen.setImageResource(R.drawable.t2edicion);
        ClearButtonStyles();
        Button btnEdicion = (Button) findViewById(R.id.buttonedicion2);
        Drawable fondo = getResources().getDrawable(R.drawable.line_orange);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            btnEdicion.setBackground(fondo);
        }
    }

    public void onClickImagenTerceraEdicion(View v){
        ImageView imagen = (ImageView)findViewById(R.id.imageViewCambiarEdicion);
        imagen.setImageResource(R.drawable.t3edicion);
        ClearButtonStyles();
        Button btnEdicion = (Button) findViewById(R.id.buttonedicion3);
        Drawable fondo = getResources().getDrawable(R.drawable.line_bluecito);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            btnEdicion.setBackground(fondo);
        }
    }

    public void onClickImagenCuartaEdicion(View v){
        ImageView imagen = (ImageView)findViewById(R.id.imageViewCambiarEdicion);
        imagen.setImageResource(R.drawable.t4edicion);
        ClearButtonStyles();
        Button btnEdicion = (Button) findViewById(R.id.buttonedicion4);
        Drawable fondo = getResources().getDrawable(R.drawable.line_cyan);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            btnEdicion.setBackground(fondo);
        }
    }

    public void ClearButtonStyles(){
        Button btnEdicion1 = (Button) findViewById(R.id.buttonedicion1);
        Button btnEdicion2 = (Button) findViewById(R.id.buttonedicion2);
        Button btnEdicion3 = (Button) findViewById(R.id.buttonedicion3);
        Button btnEdicion4 = (Button) findViewById(R.id.buttonedicion4);
        int primaryDark = getResources().getColor(R.color.colorPrimaryDark);
        int bluecito = getResources().getColor(R.color.colorBluecito);
        int cyan = getResources().getColor(R.color.colorCyan);
        int orange = getResources().getColor(R.color.colorOrange);
        btnEdicion1.setBackgroundColor(primaryDark);
        btnEdicion2.setBackgroundColor(orange);
        btnEdicion3.setBackgroundColor(bluecito);
        btnEdicion4.setBackgroundColor(cyan);
    }

}
