package com.solprob.yadierq87.consumidores.menu_parent_principal;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.solprob.yadierq87.consumidores.R;
import com.solprob.yadierq87.consumidores.clases_dominio.usuario_clasificados;
import com.solprob.yadierq87.consumidores.helper.CustomHttpClient;
import com.solprob.yadierq87.consumidores.pantallas_mensajes.Msg_pt4_exito_registro_usuario;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

public class Pt3_Crear_cuenta_usuarios extends AppCompatActivity {

    public usuario_clasificados user;
    DefaultHttpClient httpclient;
    private static String url_api = "http://192.168.137.1/now-dev/api_consumidores/methods/postInsert.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_crear_cuenta_usuarios);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    private void lanzarIntentMessageRegistrousuario() {
        finish();
        startActivityForResult(new Intent(this, Msg_pt4_exito_registro_usuario.class), 1);
    }

    public void onClickCreateNuevoUsuario(View v){

        EditText nro_recomendacion = (EditText) findViewById(R.id.editTextNroRecomendacion);
        EditText nombres = (EditText) findViewById(R.id.editTextNombreApellidos);
        EditText email = (EditText) findViewById(R.id.editTextEmailUser);
        EditText num_celular = (EditText) findViewById(R.id.editTextNumeroCelular);
        EditText nickname = (EditText) findViewById(R.id.editTextNickname);
        EditText password = (EditText) findViewById(R.id.editTextPasswordUser);
        String recomendacion = nro_recomendacion.getText().toString();
        String nombre_apellidos = nombres.getText().toString();
        String email_user = email.getText().toString();
        String celular = num_celular.getText().toString();
        String usuario_nick = nickname.getText().toString();
        String passwd = password.getText().toString();
        user = new usuario_clasificados(recomendacion,nombre_apellidos,email_user,celular,usuario_nick,passwd);
        //// TODO: 3/18/2018 create bundle info that insert user in web service                

       new HttpTaskInsertUser().execute(url_api);
        //dummy method Dialogo de confirmacion
       lanzarIntentMessageRegistrousuario();
    }


    public class HttpTaskInsertUser extends AsyncTask<String,Void,String> {

        protected String doInBackground(String... params) {

            ArrayList values =new ArrayList<>();
            values.add(new BasicNameValuePair("table","usuario_clasificados"));
            values.add(new BasicNameValuePair("action","Insert"));
            values.add(new BasicNameValuePair("nro_recomendacion",user.getNro_recomendacion()));
            values.add(new BasicNameValuePair("nombre_apellidos",user.getNombre_apellidos()));
            values.add(new BasicNameValuePair("email",user.getEmail_user()));
            values.add(new BasicNameValuePair("num_celular",user.getNum_celular()));
            values.add(new BasicNameValuePair("usuario_nick",user.getNickname()));
            values.add(new BasicNameValuePair("passwd",user.getPassword()));

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
           Toast msg = Toast.makeText(getApplicationContext(),"Insertando Usuario " + result,Toast.LENGTH_LONG);
           msg.show();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }



}
