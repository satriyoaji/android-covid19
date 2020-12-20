package com.covidata.application.model;

public class TotalData {
    private int jumlah_positif;
    private int jumlah_dirawat;
    private int jumlah_sembuh;
    private int jumlah_meninggal;
    private String created;

    public TotalData(int jumlah_positif, int jumlah_dirawat, int jumlah_sembuh, int jumlah_meninggal, String created) {
        this.jumlah_positif = jumlah_positif;
        this.jumlah_dirawat = jumlah_dirawat;
        this.jumlah_sembuh = jumlah_sembuh;
        this.jumlah_meninggal = jumlah_meninggal;
        this.created = created;
    }

    public TotalData() {
    }

    public int getJumlah_positif() {
        return jumlah_positif;
    }

    public int getJumlah_dirawat() {
        return jumlah_dirawat;
    }

    public int getJumlah_sembuh() {
        return jumlah_sembuh;
    }

    public int getJumlah_meninggal() {
        return jumlah_meninggal;
    }

    public String getCreated() {
        return created;
    }
}
