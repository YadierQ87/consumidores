package com.solprob.yadierq87.consumidores.menu_parent_principal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.solprob.yadierq87.consumidores.R;

public class Publicaciones_en_Diario_Clasificados_Digital extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicaciones_en_diario_clasificados_digital);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        LlenarSpinnersDatos();
    }

    public void LlenarSpinnersDatos(){
        final String[] formato = new String[]{ "Buenos Aires" ,"Catamarca" ,"Chaco" };
        final String[] categoria = new String[]{ "Buenos Aires" ,"Catamarca" ,"Chaco" };
        ArrayAdapter<String> adaptadorFormato =  new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, formato);
        ArrayAdapter<String> adaptadorCategoria =  new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, categoria);


        final Spinner cmbOpciones = (Spinner)findViewById(R.id.spinnerFormat);
        adaptadorFormato.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        cmbOpciones.setAdapter(adaptadorFormato);

        final Spinner cmbOpciones2 = (Spinner)findViewById(R.id.spinnerCategory);
        adaptadorCategoria.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        cmbOpciones.setAdapter(adaptadorCategoria);
    }

}
