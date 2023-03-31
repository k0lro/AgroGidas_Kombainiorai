package com.example.agrogidas.users;

public class Kombainai {
    String marke;
    String modelis;
    int darbinis_plotis;
    int metai;
    String aprasas;
    int kaina;
    String nuotrauka;

    public Kombainai(){

    }

    public Kombainai(String marke, String modelis, int darbinis_plotis, int metai, String aprasas, int kaina, String nuotrauka) {
        this.marke = marke;
        this.modelis = modelis;
        this.darbinis_plotis = darbinis_plotis;
        this.metai = metai;
        this.aprasas = aprasas;
        this.kaina = kaina;
        this.nuotrauka = nuotrauka;
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
}
