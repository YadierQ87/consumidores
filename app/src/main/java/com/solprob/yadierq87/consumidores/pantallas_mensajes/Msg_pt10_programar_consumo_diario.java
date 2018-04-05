package com.solprob.yadierq87.consumidores.pantallas_mensajes;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.solprob.yadierq87.consumidores.R;
import com.solprob.yadierq87.consumidores.clases_dominio.consumo_diario;

public class Msg_pt10_programar_consumo_diario extends AppCompatActivity {

    consumo_diario consumo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_programar_consumo_diario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        LlenarSpinnerProvincias();
    }

    public void LlenarSpinnerProvincias(){
        final String[] datos = new String[]{ "Buenos Aires" ,"Catamarca" ,"Chaco" ,
                "Chubut" ,"Córdoba" ,"Corrientes" ,"Entre Ríos" ,
                "Formosa" ,"Jujuy" ,"La Pampa" ,"La Rioja" ,
                "Mendoza" ,"Misiones" ,"Neuquén" ,"Río Negro" ,
                "Salta" ,"San Juan" ,"San Luis" ,"Santa Cruz" ,
                "Santa Fe" ,"Santiago del Estero" ,
                "Tierra del Fuego, Antártida e Islas del Atlántico Sur"};
        ArrayAdapter<String> adaptador =  new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, datos);
        final Spinner cmbOpciones = (Spinner)findViewById(R.id.spinnerProvincia);
        adaptador.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        cmbOpciones.setAdapter(adaptador);
    }

    // go to pantalla 11 Programar Consumo Diario
    public void onclickPantalla11Mapas(View v){

        startActivityForResult(new Intent(this, Msg_pt14_video_pqherramientas_digitales.class), 1);

        //Arreglar cuando lleguen los mapas
        // TODO: 3/21/2018  metodo salvar datos en la bd web/
        //startActivityForResult(new Intent(this, Mapa_pt11_activar_gps.class), 1);
    }    
    
}
