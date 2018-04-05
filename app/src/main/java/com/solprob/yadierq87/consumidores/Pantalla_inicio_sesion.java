package com.solprob.yadierq87.consumidores;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.solprob.yadierq87.consumidores.clases_dominio.usuario_clasificados;
import com.solprob.yadierq87.consumidores.helper.HttpHandler;
import com.solprob.yadierq87.consumidores.menu_parent_principal.Pt3_Crear_cuenta_usuarios;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.google.android.gms.internal.zzt.TAG;

public class Pantalla_inicio_sesion extends AppCompatActivity {

    public SharedPreferences prefs;
    ArrayList<usuario_clasificados> listUsers;
    private ProgressDialog pDialog;
    private static String url_api = "http://192.168.137.1/now-dev/api_consumidores/usuario_clasificados";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_pantalla_inicio_sesion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       // RevisarSesionActiva();
       // new GetContactsAsyncTask().execute();
    }

    public void RevisarSesionActiva(){
        prefs =  getSharedPreferences("PreferenciasSesion", Context.MODE_PRIVATE);
        EditText inputNick = (EditText) findViewById(R.id.editText10);
        EditText inputPassw = (EditText) findViewById(R.id.editText11);
        String correo = prefs.getString("email","admin");
        String passwd = prefs.getString("passwd","admin");
        Toast.makeText(this,correo + " - " + passwd,Toast.LENGTH_LONG).show();
        if(correo != "" && passwd != ""){
            inputNick.setText(correo);
            inputPassw.setText(passwd);
        }
    }


    public void onClickLoginUser(View v){
        EditText inputNick = (EditText) findViewById(R.id.editText10);
        EditText inputPassw = (EditText) findViewById(R.id.editText11);
        //// TODO: 3/18/2018 auth con db en server web
        if(inputNick.getText().toString().equals("admin") && inputPassw.getText().toString().equals("admin"))
        {
            finish();
            startActivityForResult(new Intent(this, Principal_menu.class), 1);
        }
        /*
        int max = 0;
        if(listUsers.size() > 0)
            max = listUsers.size();
        for (int i=0; i < max; i++) {
            String nombre = listUsers.get(i).getNickname();
            String passwd = listUsers.get(i).getPassword();
            if(inputNick.getText().toString().equals(nombre))
                if(inputPassw.getText().toString().equals(passwd))
                {
                    String msg = "Autenticacion TRUE";
                    Toast toast = Toast.makeText(this.getApplicationContext(), msg, Toast.LENGTH_SHORT);
                    toast.show();
                    finish();
                    startActivityForResult(new Intent(this, Principal_menu.class), 1);
                }
        }
        // send the webtoken
        //  return auth key
        //dummy method
        if((inputNick.getText().toString().equals("admin"))  && (inputPassw.getText().toString().equals("admin")) ) {
            String msg = "Autenticacion TRUE";
            Toast toast = Toast.makeText(this.getApplicationContext(), msg, Toast.LENGTH_SHORT);
            toast.show();
            CheckBox sesion = (CheckBox)findViewById(R.id.checkBoxSesionActiva);
            if (sesion.isChecked()) {
                //salvar las nuevas preferencias
                PreferenciasSesionActiva(inputNick.getText().toString(),inputPassw.getText().toString());
            }
            //go to principal menu
            finish();
            startActivityForResult(new Intent(this, Principal_menu.class), 1);
        }*/
        else {
            String msg = "User or password wrong!";
            Toast toast = Toast.makeText(this.getApplicationContext(), msg, Toast.LENGTH_SHORT);
            toast.show();
            inputNick.setText("");
            inputPassw.setText("");
        }
    }

    public void onClickOlvideMiPassword(View v){
        finish();
        startActivityForResult(new Intent(this, Olvide_mi_password.class), 1);
    }

    public void onClickIntentCrearCuenta(View v) {
        finish();
        startActivityForResult(new Intent(this, Pt3_Crear_cuenta_usuarios.class),1);
    }

    //si el usuario quiere mantener la sesion activa
    public void PreferenciasSesionActiva(String userapp,String passwdapp){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("email", userapp);
        editor.putString("passwd", passwdapp);
        editor.commit();
    }


    private class GetContactsAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(Pantalla_inicio_sesion.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
            listUsers = new ArrayList<>();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url_api);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONArray objetos = new JSONArray(jsonStr);
                    // looping through All Contacts
                    for (int i = 0; i < objetos.length(); i++) {
                        JSONObject c = objetos.getJSONObject(i);
                        String email = c.getString("email");
                        String passwd = c.getString("passwd");
                        String nro_recomendacion = c.getString("nro_recomendacion");
                        String num_celular = c.getString("num_celular");
                        String nickname = c.getString("usuario_nick");
                        String nombre_apellidos = c.getString("nombre_apellidos");
                        usuario_clasificados user = new usuario_clasificados(nro_recomendacion,
                                nombre_apellidos,email,num_celular,nickname,passwd);
                        listUsers.add(i,user);
                    }
                    Log.e(TAG, "Json list: " + listUsers);
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
        }

    }



}
