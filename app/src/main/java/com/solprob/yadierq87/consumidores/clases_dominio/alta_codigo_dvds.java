package com.solprob.yadierq87.consumidores.clases_dominio;

import java.util.Date;

/**
 * Created by Yadier on 3/30/2018.
 */

public class alta_codigo_dvds {



    public String getNumero_dvd() {
        return numero_dvd;
    }

    public Date getFecha_activacion() {
        return fecha_activacion;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public alta_codigo_dvds(String numero_dvd, Date fecha_activacion, int id_usuario) {
        this.numero_dvd = numero_dvd;
        this.fecha_activacion = fecha_activacion;
        this.id_usuario = id_usuario;
    }

    public void getInsertQuery(){
       // INSERT INTO `alta_codigo_dvds`(`id`, `numero_dvd`, `fecha_activacion`, `id_usuario`) VALUES ([value-1],[value-2],[value-3],[value-4])
    }

    String numero_dvd ;
    Date fecha_activacion ;
    int id_usuario  ;
}
