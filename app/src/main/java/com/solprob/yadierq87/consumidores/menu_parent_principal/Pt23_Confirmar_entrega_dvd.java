package com.solprob.yadierq87.consumidores.menu_parent_principal;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.solprob.yadierq87.consumidores.R;
import com.solprob.yadierq87.consumidores.clases_dominio.confirmacion_entrega_dvd;
import com.solprob.yadierq87.consumidores.helper.CustomHttpClient;
import com.solprob.yadierq87.consumidores.vistas_mapas.Mapa_pt11_activar_gps;

import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

public class Pt23_Confirmar_entrega_dvd extends AppCompatActivity {

    private static final int TOMAR_FOTO_PERSONA = 1;
    private static final int TOMAR_FOTO_CASA = 2;
    private Uri fotoSeleccionada;
    public confirmacion_entrega_dvd confirmado;
    private static String url_api = "http://192.168.137.1/now-dev/api_consumidores/methods/postInsert.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_entrega_dvd);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void onClickConfirmarEntregaDvdGoToPantallaGPS(View v){
        // TODO: 3/28/2018  El campo e-mail no es obligatorio. todos los demas campos deberan ser obligatorio.
        // metodo para insertar en bd los campos y guardar las fotos
        EditText textfecha_retirar_dvd = (EditText) findViewById(R.id.editTextNroRecomendacion);
        EditText texthora_retiro_dvd = (EditText) findViewById(R.id.editTextPasswordUser);
        EditText textemail = (EditText) findViewById(R.id.editTextNombreApellidos);
        EditText textnum_celular = (EditText) findViewById(R.id.editTextEmailUser);
        EditText textlocalidad = (EditText) findViewById(R.id.editTextNumeroCelular);
        EditText textnombre_apellidos = (EditText) findViewById(R.id.editTextNickname);



        String fecha_retirar_dvd = textfecha_retirar_dvd.getText().toString();
        String hora_retiro_dvd = texthora_retiro_dvd.getText().toString();
        String email = textemail.getText().toString();
        String num_celular = textnum_celular.getText().toString();
        String localidad = textlocalidad.getText().toString();
        String nombre_apellidos = textnombre_apellidos.getText().toString();
        String fotocasa;
        String fotopersona=fotocasa="";

        confirmado = new confirmacion_entrega_dvd(fotopersona,fotocasa,hora_retiro_dvd,
                     nombre_apellidos,localidad,num_celular,email,fecha_retirar_dvd,1);

        new HttpTaskInsertUser().execute(url_api);
        finish();
        startActivityForResult(new Intent(this, Mapa_pt11_activar_gps.class), 1);
    }

    private boolean checkCameraPermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        return true;
    }
    //Escoger la accion a seleccionar la foto si galeria , no camara
    public Dialog DialogoSeleccionFotos(final int accionCode)
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seleccione Acción");
        String msg = "Escoger foto desde:";
        builder.setMessage(msg);
        builder.setPositiveButton("Galeria", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                LanzarIntentGaleria(accionCode);
            }
        });
        builder.setNegativeButton("Cámara", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                LanzarIntentCamara(accionCode);
            }
        });
        return builder.create();
    }

    public void LanzarIntentCamara(int codeCamara){
        Intent camaraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camaraIntent, codeCamara);
    }

    public void LanzarIntentGaleria(int codeGaleria){
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, codeGaleria);
    }

    public void onclickHacerFotoPersona(View v){
        Dialog dialogo =  DialogoSeleccionFotos(TOMAR_FOTO_PERSONA);
        dialogo.show();
    }

    public void onclickHacerFotoCasa(View v){
        Dialog dialogo =  DialogoSeleccionFotos(TOMAR_FOTO_CASA);
        dialogo.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if  (requestCode == TOMAR_FOTO_PERSONA) {
            /*Bitmap imagen = (Bitmap) data.getExtras().get("data");
            ImageView iv = (ImageView)findViewById(R.id.imageButtonPersona);
            iv.setImageBitmap(imagen);*/
        }
        if  (requestCode == TOMAR_FOTO_CASA) {
            /*Bitmap imagen = (Bitmap) data.getExtras().get("data");
            ImageView iv = (ImageView)findViewById(R.id.imageButtonCasa);
            iv.setImageBitmap(imagen);*/
        }
    }

    public class HttpTaskInsertUser extends AsyncTask<String,Void,String> {

        protected String doInBackground(String... params) {

           /* this.dir_url_photo_persona = dir_url_photo_persona;
            this.dir_url_photo_casa = dir_url_photo_casa;
            this.hora_retiro_dvd = hora_retiro_dvd;
            this.nombre_apellidos = nombre_apellidos;
            this.localidad = localidad;
            this.num_celular = num_celular;
            this.email = email;
            this.fecha_retirar_dvd = fecha_retirar_dvd;
            this.id_usuario = id_usuario*/


            ArrayList values =new ArrayList<>();
            values.add(new BasicNameValuePair("table","usuario_clasificados"));
            values.add(new BasicNameValuePair("action","Insert"));
            values.add(new BasicNameValuePair("dir_url_photo_persona",confirmado.getDir_url_photo_persona()));
            values.add(new BasicNameValuePair("dir_url_photo_casa",confirmado.getDir_url_photo_casa()));
            values.add(new BasicNameValuePair("hora_retiro_dvd",confirmado.getHora_retiro_dvd()));
            values.add(new BasicNameValuePair("nombre_apellidos",confirmado.getNombre_apellidos()));
            values.add(new BasicNameValuePair("localidad",confirmado.getLocalidad()));
            values.add(new BasicNameValuePair("num_celular",confirmado.getNum_celular()));
            values.add(new BasicNameValuePair("email",confirmado.getEmail()));
            values.add(new BasicNameValuePair("fecha_retirar_dvd",confirmado.getFecha_retirar_dvd()));
            values.add(new BasicNameValuePair("id_usuario","1"));

            try {
                CustomHttpClient httppost = new CustomHttpClient();
                httppost.executeHttpPost(url_api,values);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "result";
        }

        @Override
        protected void onPostExecute(String result) {
            Toast msg = Toast.makeText(getApplicationContext(),"Confirmando Entrega " + result,Toast.LENGTH_LONG);
            msg.show();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }


}
