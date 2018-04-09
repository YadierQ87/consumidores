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

    //Ejemplo de fragment actibvity AutoSearch de google

   /* public class PlacesAutoCompleteAdapter extends ArrayAdapter<String> implements Filterable {
        public static final String LOG_TAG = "ImpactForce";
        String textaddress;
        private ArrayList<String> resultList = new ArrayList<String>();
        private LayoutInflater inflater;
        private ViewHolder holder;

        public PlacesAutoCompleteAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return resultList.size();
        }

        @Override
        public String getItem(int index) {
            if (resultList.size() > index) {
                return resultList.get(index);
            }
            return "";
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getMyView(position, convertView, parent);
        }

        private View getMyView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.autocomplete_list_text, parent, false);
                holder = new ViewHolder();

                holder.addressTitle = (TextView) convertView.findViewById(R.id.textViewTitleHeader);
                holder.address = (TextView) convertView.findViewById(R.id.textViewTitleSub);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            try {
                String address = getItem(position);

                String add[] = address.split(",");

                holder.addressTitle.setText(add[0]);
                holder.address.setText(address);
            } catch (Exception e) {
            }
            return convertView;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getMyView(position, convertView, parent);
        }

        @Override
        public Filter getFilter() {
            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults filterResults = new FilterResults();
                    if (constraint != null) {
                        // Retrieve the autocomplete results.
                        if (Utility.isOnline(getContext())) {
                            resultList = autocomplete(constraint.toString());

                            // Assign the data to the FilterResults
                            filterResults.values = resultList;
                            filterResults.count = resultList.size();
                        }

                    }
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint,
                                              FilterResults results) {
                    if (results != null && results.count > 0) {
                        notifyDataSetChanged();
                    } else {
                        notifyDataSetInvalidated();
                    }
                }
            };
            return filter;
        }

        private ArrayList<String> autocomplete(String input) {

            HttpURLConnection conn = null;
            StringBuilder jsonResults = new StringBuilder();
            try {
                //https://maps.googleapis.com/maps/api/place/autocomplete/json?input=Paris&types=geocode&key=YOUR_API_KEY
                StringBuilder sb = new StringBuilder(NetworkConstant.PLACES_API_BASE
                        + NetworkConstant.TYPE_AUTOCOMPLETE + NetworkConstant.OUT_JSON);
                //sb.append("&types=geocode&key=" + Constant.PLACES_AUTOCOMPLETE_API_KEY);
                sb.append("?sensor=false&key=" + NetworkConstant.PLACES_AUTOCOMPLETE_API_KEY);

                // sb.append("&location=" + BeanLocation.getLocation().getLatitude()
                // + "," + BeanLocation.getLocation().getLongitude());
                sb.append("&radius=500");
                sb.append("&input=" + URLEncoder.encode(input, "utf8"));
                Log.w("url data", "" + sb.toString());
                // AppLog.Log("PlaceAdapter", "Place Url : " + sb.toString());
                URL url = new URL(sb.toString());
                conn = (HttpURLConnection) url.openConnection();
                InputStreamReader in = new InputStreamReader(conn.getInputStream());
                Log.w("url data", "" + sb.toString());
                // Load the results into a StringBuilder
                int read;
                char[] buff = new char[1024];
                while ((read = in.read(buff)) != -1) {
                    jsonResults.append(buff, 0, read);
                }
            } catch (MalformedURLException e) {
                Log.e(LOG_TAG, "Error processing Places API URL", e);
                return resultList;
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error connecting to Places API", e);
                return resultList;
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }

            try {
                // Create a JSON object hierarchy from the results
                // System.out.println(jsonResults.toString());
                JSONObject jsonObj = new JSONObject(jsonResults.toString());
                JSONArray predsJsonArray = jsonObj.getJSONArray("predictions");

                // Extract the Place descriptions from the results
                resultList.clear();
                for (int i = 0; i < predsJsonArray.length(); i++) {
                    resultList.add(predsJsonArray.getJSONObject(i).getString(
                            "description"));
                }
            } catch (JSONException e) {
                Log.e(LOG_TAG, "Cannot process JSON results", e);
            }

            return resultList;
        }

        public class ViewHolder {

            TextView address;
            TextView addressTitle;
        }
    }*/


}