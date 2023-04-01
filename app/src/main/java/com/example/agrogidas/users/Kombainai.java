package com.example.agrogidas.users;

public class Kombainai {
    String marke;
    String modelis;
    int darbinis_plotis;
    int metai;
    String aprasas;
    int kaina;
    String nuotrauka;

    String telefonas;

    String miestas;
    int galia;
    int motovalandos;

    public Kombainai(){

    }

    public Kombainai(String marke, String modelis, int darbinis_plotis, int metai, String aprasas,
                     int kaina, String nuotrauka, String telefonas, String miestas, int galia,
                     int motovalandos) {
        this.marke = marke;
        this.modelis = modelis;
        this.darbinis_plotis = darbinis_plotis;
        this.metai = metai;
        this.aprasas = aprasas;
        this.kaina = kaina;
        this.nuotrauka = nuotrauka;
        this.telefonas = telefonas;
        this.miestas = miestas;
        this.galia = galia;
        this.motovalandos = motovalandos;
    }

    public String getMarke() {
        return marke;
    }

    public void setMarke(String marke) {
        this.marke = marke;
    }

    public String getModelis() {
        return modelis;
    }

    public void setModelis(String modelis) {
        this.modelis = modelis;
    }

    public int getDarbinis_plotis() {
        return darbinis_plotis;
    }

    public void setDarbinis_plotis(int darbinis_plotis) {
        this.darbinis_plotis = darbinis_plotis;
    }

    public int getMetai() {
        return metai;
    }

    public void setMetai(int metai) {
        this.metai = metai;
    }

    public String getAprasas() {
        return aprasas;
    }

    public void setAprasas(String aprasas) {
        this.aprasas = aprasas;
    }

    public int getKaina() {
        return kaina;
    }

    public void setKaina(int kaina) {
        this.kaina = kaina;
    }

    public String getNuotrauka() {
        return nuotrauka;
    }

    public void setNuotrauka(String nuotrauka) {
        this.nuotrauka = nuotrauka;
    }

    public String getTelefonas() { return telefonas; }

    public void setTelefonas(String telefonas) { this.telefonas = telefonas; }

    public String getMiestas() { return miestas; }

    public void setMiestas(String miestas) { this.miestas = miestas; }

    public int getGalia() { return galia; }

    public void setGalia(int galia) { this.galia = galia; }

    public int getMotovalandos() { return motovalandos; }

    public void setMotovalandos(int motovalandos) { this.motovalandos = motovalandos; }
}
