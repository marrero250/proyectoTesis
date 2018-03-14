package com.example.josephmarrero.cantv.data.api.model;

/**
 * Created by marre on 15/3/2017.
 */

/**
 * Objeto plano Java para representar afiliados
 */

public class Affiliate {
    private String numero_cuenta;
    private String telefono;
    private String id;
    private String name;
    private String address;
    private String gender;
    private String token;



    public Affiliate(String id, String name, String address, String gender, String token, String telefono, String numero_cuenta) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.token = token;
        this.telefono = telefono;
        this.numero_cuenta = numero_cuenta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNumero_cuenta() {
        return numero_cuenta;
    }

    public void setNumero_cuenta(String numero_cuenta) {
        this.numero_cuenta = numero_cuenta;
    }



    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
