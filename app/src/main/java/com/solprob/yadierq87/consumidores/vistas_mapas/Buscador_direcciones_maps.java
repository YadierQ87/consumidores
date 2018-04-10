package com.solprob.yadierq87.consumidores.vistas_mapas;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
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

import java.util.List;

public class Buscador_direcciones_maps extends FragmentActivity implements OnMapReadyCallback {

    //private final Place place = null;
    private GoogleMap mapa;
    LocationManager locManager;
    private List<Address> address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscador_direcciones_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapSearch);
        mapFragment.getMapAsync(this);


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
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;
        LatLng position;
        //Si el GPS no está habilitado
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
        googleMap.addMarker(new MarkerOptions().position(position).title("Marker mi posicion"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(position));
        googleMap.setMyLocationEnabled(true);
        /** Called when the user clicks a marker. */
        googleMap.setOnInfoWindowClickListener((GoogleMap.OnInfoWindowClickListener) this);
    }

    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this, "Info window clicked",
                Toast.LENGTH_SHORT).show();
    }


}
