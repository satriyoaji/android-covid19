package com.covidata.application.model;

public class Adding {
    private int positif, sembuh, meninggal;

    public Adding(int positif, int sembuh, int meninggal) {
        this.positif = positif;
        this.sembuh = sembuh;
        this.meninggal = meninggal;
    }

    public int getPositif() {
        return positif;
    }

    public void setPositif(int positif) {
        this.positif = positif;
    }

    public int getSembuh() {
        return sembuh;
    }

    public void setSembuh(int sembuh) {
        this.sembuh = sembuh;
    }

    public int getMeninggal() {
        return meninggal;
    }

    public void setMeninggal(int meninggal) {
        this.meninggal = meninggal;
    }
}
