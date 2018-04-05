package com.solprob.yadierq87.consumidores.vistas_mapas;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.solprob.yadierq87.consumidores.R;

import java.io.IOException;
import java.util.List;

public class Mapa_example_buscador extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_example_buscador);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mapa = (MapView)findViewById(R.id.mapa);

        input = (EditText)findViewById(R.id.editText1);
        boton = (Button)findViewById(R.id.button1);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                direccion = input.getText().toString();
                if(direccion.equals("")){
                    toast("No hay dirección para buscar : (");
                }else{
                    toast("Buscando \""+direccion+"\"");
                    Geocoder coder = new Geocoder(getApplicationContext());
                    try {
                        address = coder.getFromLocationName(direccion, 1);
                        Address location = address.get(0);
                        int lat = (int) (location.getLatitude()*1E6);
                        int lon = (int) (location.getLongitude()*1E6);
                        LatLng loc = new LatLng(lat,lon);
                        //mapa.addMarker(new MarkerOptions().position(loc).title("Marker in Buenos Aires"));
                    } catch (IOException e) {
                        toast("No se ha encontrado la dirección : (");
                    }
                }
                // Ocultar el teclado
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(input.getWindowToken(), 0);
            }
        });

    }

    private MapView mapa;
    private Button boton;
    private EditText input;
    private String direccion;
    private List<Address> address;

    public void toast(String mensaje){
        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
    }

}
