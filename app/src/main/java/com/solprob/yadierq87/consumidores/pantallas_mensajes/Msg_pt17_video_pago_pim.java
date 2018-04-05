package com.solprob.yadierq87.consumidores.pantallas_mensajes;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.solprob.yadierq87.consumidores.MainActivity;
import com.solprob.yadierq87.consumidores.R;
import com.solprob.yadierq87.consumidores.videos_view.Video_view_publicidad_nuevo;
import com.solprob.yadierq87.consumidores.videos_view.Video_view_video_pago_pim;

public class Msg_pt17_video_pago_pim extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_video_pago_pim);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void onClickSetUpVideoView(View v) {
        try {
            Intent videoview = new Intent(this, Video_view_video_pago_pim.class);
            videoview.putExtra("force_fullscreen",true);
            startActivity(videoview);
        } catch (ActivityNotFoundException e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
    }


    public void onClickGo18ConfirmarPagosPim(View v){
        finish();
        startActivityForResult(new Intent(this, Msg_pt18_confirmar_pago_pim.class), 1);
    }

    public void onClickCerrarSesion(View v) {
        Dialog cerrar_sesion = DialogoSalirSesion();
        cerrar_sesion.show();
    }

    public Dialog DialogoSalirSesion()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Informe Cierre de Sesion");
        String msg = "Salir a pantalla principal";
        builder.setMessage(msg);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                //lanzar video  Publicidad de Nuevo Diario Clasificados Digital,
                lanzarIntentMain();
            }
        });
        return builder.create();
    }

    public void lanzarIntentMain(){
        finish();
        startActivityForResult(new Intent(this, MainActivity.class), 1);
    }

}
