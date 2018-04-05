package com.solprob.yadierq87.consumidores.clases_dominio;

import java.sql.Date;

/**
 * Created by Yadier on 3/30/2018.
 */

public class compra_dvd {

    public compra_dvd(int id_usuario, int cantd_dvd, Date fecha_compra) {
        this.id_usuario = id_usuario;
        this.cantd_dvd = cantd_dvd;
        this.fecha_compra = fecha_compra;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public int getCantd_dvd() {
        return cantd_dvd;
    }

    public Date getFecha_compra() {
        return fecha_compra;
    }

    int id_usuario , cantd_dvd;
    Date fecha_compra ;
}
