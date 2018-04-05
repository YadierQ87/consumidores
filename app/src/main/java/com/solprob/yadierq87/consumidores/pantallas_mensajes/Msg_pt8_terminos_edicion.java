package com.solprob.yadierq87.consumidores.pantallas_mensajes;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.solprob.yadierq87.consumidores.MainActivity;
import com.solprob.yadierq87.consumidores.R;

public class Msg_pt8_terminos_edicion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_pantalla_select_edicion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void onclickContinuar(View v){
        final RadioGroup rg = (RadioGroup)findViewById(R.id.radio_opcion);
        int idSeleccionado = rg.getCheckedRadioButtonId();
        if(idSeleccionado == R.id.radioButtonSi){
            //continuar a pantalla 9
            lanzarIntentPromocionLanzamiento();
        }
        if(idSeleccionado == R.id.radioButtonNo){
            //cierre de sesion
            Dialog dialogo =  DialogoSalirSesion();
            dialogo.show();
        }
        if(idSeleccionado <= 0) {
            Toast.makeText(this,"Tiene que seleccionar alguna accion para Continuar",Toast.LENGTH_LONG).show();
        }
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
                lanzarIntentMainActivity();
            }
        });
        return builder.create();
    }

    private void lanzarIntentMainActivity() {
        finish();
        startActivityForResult(new Intent(this, MainActivity.class), 1);
    }

    private void lanzarIntentPromocionLanzamiento() {
        finish();
        startActivityForResult(new Intent(this, Msg_pt9_promocion_lanzamiento.class), 1);
    }

}
