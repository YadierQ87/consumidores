package com.solprob.yadierq87.consumidores.clases_dominio;

/**
 * Created by Yadier on 3/30/2018.
 */

public class ubicacion_entrega_dvd {

    public ubicacion_entrega_dvd(float geo_latitud, float geo_longitud, int id_usuario, int id_confirmacion_entrega) {
        this.geo_latitud = geo_latitud;
        this.geo_longitud = geo_longitud;
        this.id_usuario = id_usuario;
        this.id_confirmacion_entrega = id_confirmacion_entrega;
    }

    float geo_latitud;

    public float getGeo_latitud() {
        return geo_latitud;
    }

    public float getGeo_longitud() {
        return geo_longitud;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public int getId_confirmacion_entrega() {
        return id_confirmacion_entrega;
    }

    float geo_longitud ;
   int id_usuario,  id_confirmacion_entrega  ;

}
