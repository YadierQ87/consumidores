package com.solprob.yadierq87.consumidores.clases_dominio;

/**
 * Created by Yadier on 3/30/2018.
 */

public class confirmacion_entrega_dvd {

    public confirmacion_entrega_dvd(String dir_url_photo_persona, String dir_url_photo_casa,
                                    String hora_retiro_dvd, String nombre_apellidos,
                                    String localidad, String num_celular,
                                    String email, String fecha_retirar_dvd,
                                    int id_usuario) {
        this.dir_url_photo_persona = dir_url_photo_persona;
        this.dir_url_photo_casa = dir_url_photo_casa;
        this.hora_retiro_dvd = hora_retiro_dvd;
        this.nombre_apellidos = nombre_apellidos;
        this.localidad = localidad;
        this.num_celular = num_celular;
        this.email = email;
        this.fecha_retirar_dvd = fecha_retirar_dvd;
        this.id_usuario = id_usuario;
    }

    public String getDir_url_photo_persona() {
        return dir_url_photo_persona;
    }

    public String getDir_url_photo_casa() {
        return dir_url_photo_casa;
    }

    public String getHora_retiro_dvd() {
        return hora_retiro_dvd;
    }

    public String getNombre_apellidos() {
        return nombre_apellidos;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getNum_celular() {
        return num_celular;
    }

    public String getEmail() {
        return email;
    }

    public String getFecha_retirar_dvd() {
        return fecha_retirar_dvd;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    String dir_url_photo_persona ,dir_url_photo_casa , hora_retiro_dvd , nombre_apellidos  ;
    String localidad , num_celular , email;
    String fecha_retirar_dvd ;
    int id_usuario ;
}
