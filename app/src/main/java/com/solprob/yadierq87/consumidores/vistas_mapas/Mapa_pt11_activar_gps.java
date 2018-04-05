package com.solprob.yadierq87.consumidores.vistas_mapas;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.solprob.yadierq87.consumidores.R;
import com.solprob.yadierq87.consumidores.pantallas_mensajes.Msg_pt14_video_pqherramientas_digitales;

import java.io.IOException;
import java.util.List;

public class Mapa_pt11_activar_gps extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mapa;
    LocationManager locManager;
    private List<Address> address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_pt11_activar_gps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        try {
            mapFragment.getMapAsync(this);
        }
        catch (Exception e){
            Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT).show();
        }
    }


    // go to pantalla 11 Programar Consumo Diario
    public void onClickPt14_video_pqherramientas_digitales(View v){
        // go to pantalla 14 Video pq Herramientas digitales
        finish();
        startActivityForResult(new Intent(this, Msg_pt14_video_pqherramientas_digitales.class), 1);
    }

    public void onClickBuscarAdress(View v){

        TextView adreess = (TextView)findViewById(R.id.textViewDirecciones);
        String direccion = adreess.getText().toString();
        Geocoder geo = new Geocoder(this);
        int maxResultados = 1;
        List<Address> adress = null;
        try {
            adress = geo.getFromLocationName(direccion, maxResultados);
        } catch (IOException e) {
            e.printStackTrace();
        }
        LatLng latLng = new LatLng(adress.get(0).getLatitude(), adress.get(0).getLongitude());
        mapa.addMarker(new MarkerOptions().position(latLng).title("Marker in..."));
        /*  if(direccion.equals("")){
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
        }*/
    }



    public void toast(String mensaje){
        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
    }

    //Chequea estado de la red
    private boolean isNetworkConnected(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context
                .CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info == null || !info.isConnected() || !info.isAvailable()) {
            return false;
        }
        return true;
    }

    @Override
    public void onMapReady(GoogleMap mMap) {

        mapa = mMap;
        mapa.setMyLocationEnabled(true);

        //Si el GPS no está habilitado
        locManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (!locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Dialog dialogoGPS = mostrarAvisoGpsDeshabilitado();
            dialogoGPS.show();
        }
        if (!isNetworkConnected(this)) {
            Dialog dialogoInternet = mostrarAvisoInternetDeshabilitado();
            dialogoInternet.show();
        }
        LatLng position;
        if (locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT).show();
            }
            Location mylocation = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            position = new LatLng(mylocation.getLatitude(), mylocation.getLongitude());
            mMap.addMarker(new MarkerOptions().position(position).title("Marker mi posición"));
        }
        else{
            position = new LatLng(-34.6083,-58.3712);
            mMap.addMarker(new MarkerOptions().position(position).title("Marker in Buenos Aires"));
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
        mMap.setMyLocationEnabled(true);
        /** Called when the user clicks a marker. */

    }

    public boolean onMarkerClick(final Marker marker) {
        // Retrieve the data from the marker.
        Integer clickCount = (Integer) marker.getTag();

        // Check if a click count was set, then display the click count.
        if (clickCount != null) {
            clickCount = clickCount + 1;
            marker.setTag(clickCount);
            Toast.makeText(this,
                    marker.getTitle() +
                            " has been clicked " + clickCount + " times.",
                    Toast.LENGTH_SHORT).show();
        }
        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }

    private Dialog mostrarAvisoGpsDeshabilitado() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Habilitar Servicio GPS");
        String msg = "Para utilizar esta aplicación,habilita el acceso a tu ubicación y servicios GPS desde Configuracion";
        builder.setMessage(msg);
        builder.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                //lanzar video  Publicidad de Nuevo Diario Clasificados Digital,
            }
        });
        return builder.create();
    }

    private Dialog mostrarAvisoInternetDeshabilitado() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Habilitar Conexion Internet");
        String msg = "Revisa el acceso a datos o Conexión Wifi a Internet de tu móvil";
        builder.setMessage(msg);
        builder.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                //lanzar video  Publicidad de Nuevo Diario Clasificados Digital,
            }
        });
        return builder.create();
    }

    private void mostrarPosicion(Location location) {
        if (location != null) {
          /*  lblLatitud.setText("Latitud: " + String.valueOf(location.getLatitude()));
            lblLongitud.setText("Longitud: " + String.valueOf(location.getLongitude()));
            lblPrecision.setText("Precision: " + String.valueOf(location.getAccuracy()));*/
        } else {
           /* lblLatitud.setText("Latitud: (sin_datos)");
            lblLongitud.setText("Longitud: (sin_datos)");
            lblPrecision.setText("Precision: (sin_datos)");*/
        }
    }


}