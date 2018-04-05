package com.solprob.yadierq87.consumidores.clases_dominio;

import java.sql.Date;

/**
 * Created by Yadier on 3/30/2018.
 */

public class aviso_diario_recibido {
    public aviso_diario_recibido(int id_usuario, Date fecha_aviso, String estado, String tipo_aviso) {
        this.id_usuario = id_usuario;
        this.fecha_aviso = fecha_aviso;
        this.estado = estado;
        this.tipo_aviso = tipo_aviso;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public Date getFecha_aviso() {
        return fecha_aviso;
    }

    public String getEstado() {
        return estado;
    }

    public String getTipo_aviso() {
        return tipo_aviso;
    }

    int id_usuario  ;
    Date fecha_aviso  ;
    String estado ;
    String tipo_aviso  ;
}
