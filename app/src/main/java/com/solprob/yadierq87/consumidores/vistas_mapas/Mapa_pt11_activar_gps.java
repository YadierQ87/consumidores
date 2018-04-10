package com.solprob.yadierq87.consumidores.vistas_mapas;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.solprob.yadierq87.consumidores.R;
import com.solprob.yadierq87.consumidores.pantallas_mensajes.Msg_pt14_video_pqherramientas_digitales;

import java.util.List;

public class Mapa_pt11_activar_gps extends AppCompatActivity implements GoogleMap.OnInfoWindowClickListener, OnMapReadyCallback {

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
        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i("tag", "Place: " + place.getName());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("tag", "An error occurred: " + status);
            }
        });
        try {
            mapFragment.getMapAsync(this);
        } catch (Exception e) {
            Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT).show();
        }
    }


    // go to pantalla 11 Programar Consumo Diario
    public void onClickPt14_video_pqherramientas_digitales(View v) {
        // go to pantalla 14 Video pq Herramientas digitales
        finish();
        startActivityForResult(new Intent(this, Msg_pt14_video_pqherramientas_digitales.class), 1);
    }

    public void toast(String mensaje) {
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


    public LatLng devolverPosition(Location loc) {
        LatLng position;
        if (loc != null) {
            position = new LatLng(loc.getLatitude(), loc.getLongitude());
        } else {
            position = new LatLng(-34.6083, -58.3712);
        }
        return position;
    }

    @Override
    public void onMapReady(GoogleMap mMap) {

        mapa = mMap;
        LatLng position;
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

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location mylocation = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        position = devolverPosition(mylocation);

       // position = new LatLng(-34.6083,-58.3712);
        mMap.addMarker(new MarkerOptions().position(position).title("Marker mi posicion"));
        mMap.getMaxZoomLevel();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
        //mapIntent.setPackage("com.google.android.apps.maps");
        mMap.setMyLocationEnabled(true);
        /** Called when the user clicks a marker. */
        mMap.setOnInfoWindowClickListener(this);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this, "Info window clicked",
                Toast.LENGTH_SHORT).show();
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


}