package com.solprob.yadierq87.consumidores.pantallas_mensajes;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.solprob.yadierq87.consumidores.R;

public class Msg_pt15_programar_consumo_DVD extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_programar_consumo_dvd);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        LlenarSpinnerNumeros();
    }

    public void LlenarSpinnerNumeros(){
        final Integer[] datos = new Integer[]{ 1,2,3,4,5,6,7,8,9,10 };
        ArrayAdapter<Integer> adaptador =  new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, datos);
        final Spinner cmbOpciones = (Spinner)findViewById(R.id.spinnerNumerosDvd);
        adaptador.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        cmbOpciones.setAdapter(adaptador);
    }

    public void onClickGoDetalleServicios(View v){
        // TODO: 3/21/2018
        // guardar datos en database web service
        finish();
        startActivityForResult(new Intent(this, Msg_pt16_detalle_de_Servicios.class), 1);
    }



}
