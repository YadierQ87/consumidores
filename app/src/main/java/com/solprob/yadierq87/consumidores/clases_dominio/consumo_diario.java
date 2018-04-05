package com.solprob.yadierq87.consumidores.clases_dominio;

/**
 * Created by Yadier on 3/30/2018.
 */

public class consumo_diario {

    public consumo_diario(int id_usuario, String direccion, String piso,
                          String dpto, String zip_code, String entre_calle1,
                          String entre_calle2, String provincia, String partido, String localidad) {
        this.id_usuario = id_usuario;
        this.direccion = direccion;
        this.piso = piso;
        this.dpto = dpto;
        this.zip_code = zip_code;
        this.entre_calle1 = entre_calle1;
        this.entre_calle2 = entre_calle2;
        this.provincia = provincia;
        this.partido = partido;
        this.localidad = localidad;
    }

    int id_usuario ;
    String direccion;
    String piso;
    String dpto;
    String zip_code;
    String entre_calle1;
    String entre_calle2;

    public int getId_usuario() {
        return id_usuario;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getPiso() {
        return piso;
    }

    public String getDpto() {
        return dpto;
    }

    public String getZip_code() {
        return zip_code;
    }

    public String getEntre_calle1() {
        return entre_calle1;
    }

    public String getEntre_calle2() {
        return entre_calle2;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getPartido() {
        return partido;
    }

    public String getLocalidad() {
        return localidad;
    }

    String provincia;
    String partido;
    String localidad;

}
