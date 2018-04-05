package com.solprob.yadierq87.consumidores.menu_parent_principal;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.solprob.yadierq87.consumidores.R;
import com.solprob.yadierq87.consumidores.clases_dominio.alta_codigo_dvds;
import com.solprob.yadierq87.consumidores.helper.CustomHttpClient;

import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

public class Pt22_Alta_codigo_seguridad_dvd extends AppCompatActivity {

    public alta_codigo_dvds codigos;
    private static String url_api = "http://192.168.137.1/now-dev/api_consumidores/methods/postInsert.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_codigo_seguridad);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void onClickGoToConfirmarEntregaDvd(View v){
        finish();
        startActivityForResult(new Intent(this, Pt23_Confirmar_entrega_dvd.class), 1);
    }

    public class HttpTaskInsertUser extends AsyncTask<String,Void,String> {

        protected String doInBackground(String... params) {

            ArrayList values =new ArrayList<>();
            values.add(new BasicNameValuePair("table","alta_codigo_dvds"));
            values.add(new BasicNameValuePair("action","Insert"));
            values.add(new BasicNameValuePair("numero_dvd",codigos.getNumero_dvd()));

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
