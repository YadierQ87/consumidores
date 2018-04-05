package com.solprob.yadierq87.consumidores;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.solprob.yadierq87.consumidores.menu_parent_principal.Pt3_Crear_cuenta_usuarios;


public class MainActivity extends Activity {

    SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        RevisarSesionActiva();
       /* if(!isNetworkConnected(this)){
            Dialog dialogoInternet = mostrarAvisoInternetDeshabilitado();
            dialogoInternet.show();
        }*/
    }

    public void RevisarSesionActiva(){
        try {
            prefs = getSharedPreferences("PreferenciasSesion",Context.MODE_PRIVATE);
            String correo = prefs.getString("email","admin");
            String passwd = prefs.getString("passwd","admin");
            Toast.makeText(this,correo + " - " + passwd,Toast.LENGTH_LONG).show();
        }
        catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
    }

    /*Metodos onclick*/
    public void onClickIntentCrearCuenta(View v) {
        startActivityForResult(new Intent(this, Pt3_Crear_cuenta_usuarios.class),1);
    }

    public void onClickIntentPantallaInicio(View v) {
        startActivityForResult(new Intent(this, Pantalla_inicio_sesion.class),1);
    }

    //Chequea estado de la red
    public boolean isNetworkConnected(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context
                .CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info == null || !info.isConnected() || !info.isAvailable()) {
            return false;
        }
        return true;
    }

    public Dialog mostrarAvisoInternetDeshabilitado() {
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
