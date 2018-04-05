package com.solprob.yadierq87.consumidores.clases_dominio;

import org.json.JSONObject;

/**
 * Created by Yadier on 3/18/2018.
 */

public class usuario_clasificados extends JSONObject{

    String nro_recomendacion;
    String nombre_apellidos;
    String email;
    String num_celular;
    String usuario_nick;
    String passwd ;

    public usuario_clasificados(String nro_recomendacion, String nombre_apellidos,
                                String email_user, String num_celular,
                                String nickname, String password) {
        this.nro_recomendacion = nro_recomendacion;
        this.nombre_apellidos = nombre_apellidos;
        this.email = email_user;
        this.num_celular = num_celular;
        this.usuario_nick = nickname;
        this.passwd = password;
    }

    public String getNro_recomendacion() {
        return nro_recomendacion;
    }
    public void setNro_recomendacion(String nro_recomendacion) {
        this.nro_recomendacion = nro_recomendacion;
    }
    public String getNombre_apellidos() {
        return nombre_apellidos;
    }
    public void setNombre_apellidos(String nombre_apellidos) {
        this.nombre_apellidos = nombre_apellidos;
    }
    public String getEmail_user() {
        return email;
    }
    public void setEmail_user(String email_user) {
        this.email = email_user;
    }
    public String getNum_celular() {
        return num_celular;
    }
    public void setNum_celular(String num_celular) {
        this.num_celular = num_celular;
    }
    public String getNickname() {
        return usuario_nick;
    }
    public void setNickname(String nickname) {
        this.usuario_nick = nickname;
    }
    public String getPassword() {
        return passwd;
    }
    public void setPassword(String password) {
        this.passwd = password;
    }


}
