package com.covidata.application.model;

import java.util.List;

public class ProvinceMap {
    private String key;
    private double doc_count;
    private String jumlah_kasus, jumlah_sembuh, jumlah_meninggal, jumlah_dirawat;
    private List<Gender> jenis_kelamin;
    private List<ClassificationAge> kelompok_umur;
    private Location lokasi;
    private Adding penambahan;

    public ProvinceMap(String key, double doc_count, String jumlah_kasus,
                       String jumlah_sembuh, String jumlah_meninggal,
                       String jumlah_dirawat, List<Gender> jenis_kelamin,
                       List<ClassificationAge> kelompok_umur, Location lokasi,
                       Adding penambahan) {
        this.key = key;
        this.doc_count = doc_count;
        this.jumlah_kasus = jumlah_kasus;
        this.jumlah_sembuh = jumlah_sembuh;
        this.jumlah_meninggal = jumlah_meninggal;
        this.jumlah_dirawat = jumlah_dirawat;
        this.jenis_kelamin = jenis_kelamin;
        this.kelompok_umur = kelompok_umur;
        this.lokasi = lokasi;
        this.penambahan = penambahan;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public double getDoc_count() {
        return doc_count;
    }

    public void setDoc_count(double doc_count) {
        this.doc_count = doc_count;
    }

    public String getJumlah_kasus() {
        return jumlah_kasus;
    }

    public void setJumlah_kasus(String jumlah_kasus) {
        this.jumlah_kasus = jumlah_kasus;
    }

    public String getJumlah_sembuh() {
        return jumlah_sembuh;
    }

    public void setJumlah_sembuh(String jumlah_sembuh) {
        this.jumlah_sembuh = jumlah_sembuh;
    }

    public String getJumlah_meninggal() {
        return jumlah_meninggal;
    }

    public void setJumlah_meninggal(String jumlah_meninggal) {
        this.jumlah_meninggal = jumlah_meninggal;
    }

    public String getJumlah_dirawat() {
        return jumlah_dirawat;
    }

    public void setJumlah_dirawat(String jumlah_dirawat) {
        this.jumlah_dirawat = jumlah_dirawat;
    }

    public List<Gender> getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(List<Gender> jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public List<ClassificationAge> getKelompok_umur() {
        return kelompok_umur;
    }

    public void setKelompok_umur(List<ClassificationAge> kelompok_umur) {
        this.kelompok_umur = kelompok_umur;
    }

    public Location getLokasi() {
        return lokasi;
    }

    public void setLokasi(Location lokasi) {
        this.lokasi = lokasi;
    }

    public Adding getPenambahan() {
        return penambahan;
    }

    public void setPenambahan(Adding penambahan) {
        this.penambahan = penambahan;
    }
}
