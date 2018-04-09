package com.solprob.yadierq87.consumidores;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.solprob.yadierq87.consumidores.menu_parent_principal.Atencion_al_cliente;
import com.solprob.yadierq87.consumidores.menu_parent_principal.Cambiar_nueva_contrasenna;
import com.solprob.yadierq87.consumidores.menu_parent_principal.Pt22_Alta_codigo_seguridad_dvd;
import com.solprob.yadierq87.consumidores.menu_parent_principal.Pt23_Confirmar_entrega_dvd;
import com.solprob.yadierq87.consumidores.menu_parent_principal.Publicaciones_en_Diario_Clasificados_Digital;
import com.solprob.yadierq87.consumidores.videos_view.Video_view_example;
import com.solprob.yadierq87.consumidores.vistas_mapas.Buscador_direcciones_maps;

public class Principal_menu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_principal_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.menu_publicaciones) {
            startActivityForResult(new Intent(this, Publicaciones_en_Diario_Clasificados_Digital.class), 1);
        }

        else if (id == R.id.menu_mapas) {
            startActivityForResult(new Intent(this, Buscador_direcciones_maps.class), 1);
        }
        else if (id == R.id.menu_videoxample) {
            startActivityForResult(new Intent(this, Video_view_example.class), 1);
        }


        else if (id == R.id.menu_recomendar_public) {
            startActivityForResult(new Intent(this, Recomendar_publicaciones.class), 1);
        } else if (id == R.id.menu_publicaciones) {
            startActivityForResult(new Intent(this, Publicaciones_en_Diario_Clasificados_Digital.class), 1);
        } else if (id == R.id.menu_confirmacion_dvd) {
            startActivityForResult(new Intent(this, Atencion_al_cliente.class), 1);
        } else if (id == R.id.menu_alta_codigo) {
            startActivityForResult(new Intent(this, Pt22_Alta_codigo_seguridad_dvd.class), 1);
        } else if (id == R.id.menu_entrega_dvd) {
            startActivityForResult(new Intent(this, Pt23_Confirmar_entrega_dvd.class), 1);
        } else if (id == R.id.menu_seguimiento_satelite) {

        } else if (id == R.id.menu_notificaciones) {

        } else if (id == R.id.menu_solicitud_dvd) {

        } else if (id == R.id.menu_atencion_cliente) {
            startActivityForResult(new Intent(this, Atencion_al_cliente.class), 1);
        } else if (id == R.id.menu_cambiar_passwd) {
            startActivityForResult(new Intent(this, Cambiar_nueva_contrasenna.class), 1);
        } else if (id == R.id.menu_close_sesion) {
            Dialog cerrar_sesion = DialogoSalirSesion();
            cerrar_sesion.show();
        } else{
            Toast.makeText(this.getApplicationContext(),"Esa opcion no se ha programado aun",Toast.LENGTH_LONG).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
