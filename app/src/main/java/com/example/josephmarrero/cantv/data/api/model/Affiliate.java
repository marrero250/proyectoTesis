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
    private boolean firewall;
    private boolean identificadorllamada;
    private boolean bloqueocero;
    private boolean stelefono;
    private boolean sinternet;
    private boolean stvsatelital;
    private String statusaveria;
    private String informacionaveria;
    private int aba;
    private int planlocal;
    private boolean buzonmensajes;
    private String token;




    public Affiliate(String id, String name, String address, String gender, String token, String telefono, String numero_cuenta, boolean firewall,
                     boolean identificadorllamada, boolean bloqueocero, boolean stelefono, boolean sinternet, boolean stvsatelital, String statusaveria, String informacionaveria,
                     int aba, int planlocal, boolean buzonmensajes) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.token = token;
        this.telefono = telefono;
        this.numero_cuenta = numero_cuenta;
        this.firewall = firewall;
        this.identificadorllamada = identificadorllamada;
        this.bloqueocero = bloqueocero;
        this.stelefono = stelefono;
        this.sinternet = sinternet;
        this.stvsatelital = stvsatelital;
        this.statusaveria = statusaveria;
        this.informacionaveria = informacionaveria;
        this.aba = aba;
        this.planlocal = planlocal;
        this.buzonmensajes = buzonmensajes;
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

    public boolean isStelefono() {
        return stelefono;
    }

    public void setStelefono(boolean stelefono) {
        this.stelefono = stelefono;
    }

    public boolean isSinternet() {
        return sinternet;
    }

    public void setSinternet(boolean sinternet) {
        this.sinternet = sinternet;
    }

    public boolean isStvsatelital() {
        return stvsatelital;
    }

    public void setStvsatelital(boolean stvsatelital) {
        this.stvsatelital = stvsatelital;
    }

    public boolean isFirewall() {
        return firewall;
    }

    public void setFirewall(boolean firewall) {
        this.firewall = firewall;
    }

    public boolean isIdentificadorllamada() {
        return identificadorllamada;
    }

    public void setIdentificadorllamada(boolean identificadorllamada) {
        this.identificadorllamada = identificadorllamada;
    }

    public boolean isBloqueocero() {
        return bloqueocero;
    }

    public void setBloqueocero(boolean bloqueocero) {
        this.bloqueocero = bloqueocero;
    }

    public String getStatusaveria() {
        return statusaveria;
    }

    public void setStatusaveria(String statusaveria) {
        this.statusaveria = statusaveria;
    }

    public String getInformacionaveria() {
        return informacionaveria;
    }

    public void setInformacionaveria(String informacionaveria) {
        this.informacionaveria = informacionaveria;
    }

    public int getAba() {
        return aba;
    }

    public void setAba(int aba) {
        this.aba = aba;
    }

    public int getPlanlocal() {
        return planlocal;
    }

    public void setPlanlocal(int planlocal) {
        this.planlocal = planlocal;
    }

    public boolean isBuzonmensajes() {
        return buzonmensajes;
    }

    public void setBuzonmensajes(boolean buzonmensajes) {
        this.buzonmensajes = buzonmensajes;
    }

}

