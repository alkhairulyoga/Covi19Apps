package com.example.covi19_apps.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProvinsiSumbar {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("kode")
    @Expose
    private String kode;
    @SerializedName("provinsi")
    @Expose
    private String provinsi;
    @SerializedName("total_odp")
    @Expose
    private String totalOdp;
    @SerializedName("pdp")
    @Expose
    private String pdp;
    @SerializedName("positif")
    @Expose
    private String positif;
    @SerializedName("odp_dalam_pemantauan")
    @Expose
    private String odpDalamPemantauan;
    @SerializedName("odp_selesai_pemantauan")
    @Expose
    private String odpSelesaiPemantauan;
    @SerializedName("pdp_masih_dirawat")
    @Expose
    private String pdpMasihDirawat;
    @SerializedName("pdp_meninggal")
    @Expose
    private String pdpMeninggal;
    @SerializedName("pdp_isolasidirumah")
    @Expose
    private String pdpIsolasidirumah;
    @SerializedName("pdp_pulangdan_sehat")
    @Expose
    private String pdpPulangdanSehat;
    @SerializedName("covid_dirawat")
    @Expose
    private String covidDirawat;
    @SerializedName("covid_isolasi_dirumah")
    @Expose
    private String covidIsolasiDirumah;
    @SerializedName("covid_meninggal")
    @Expose
    private String covidMeninggal;
    @SerializedName("covid_sembuh")
    @Expose
    private String covidSembuh;
    @SerializedName("waktu")
    @Expose
    private String waktu;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getTotalOdp() {
        return totalOdp;
    }

    public void setTotalOdp(String totalOdp) {
        this.totalOdp = totalOdp;
    }

    public String getPdp() {
        return pdp;
    }

    public void setPdp(String pdp) {
        this.pdp = pdp;
    }

    public String getPositif() {
        return positif;
    }

    public void setPositif(String positif) {
        this.positif = positif;
    }

    public String getOdpDalamPemantauan() {
        return odpDalamPemantauan;
    }

    public void setOdpDalamPemantauan(String odpDalamPemantauan) {
        this.odpDalamPemantauan = odpDalamPemantauan;
    }

    public String getOdpSelesaiPemantauan() {
        return odpSelesaiPemantauan;
    }

    public void setOdpSelesaiPemantauan(String odpSelesaiPemantauan) {
        this.odpSelesaiPemantauan = odpSelesaiPemantauan;
    }

    public String getPdpMasihDirawat() {
        return pdpMasihDirawat;
    }

    public void setPdpMasihDirawat(String pdpMasihDirawat) {
        this.pdpMasihDirawat = pdpMasihDirawat;
    }

    public String getPdpMeninggal() {
        return pdpMeninggal;
    }

    public void setPdpMeninggal(String pdpMeninggal) {
        this.pdpMeninggal = pdpMeninggal;
    }

    public String getPdpIsolasidirumah() {
        return pdpIsolasidirumah;
    }

    public void setPdpIsolasidirumah(String pdpIsolasidirumah) {
        this.pdpIsolasidirumah = pdpIsolasidirumah;
    }

    public String getPdpPulangdanSehat() {
        return pdpPulangdanSehat;
    }

    public void setPdpPulangdanSehat(String pdpPulangdanSehat) {
        this.pdpPulangdanSehat = pdpPulangdanSehat;
    }

    public String getCovidDirawat() {
        return covidDirawat;
    }

    public void setCovidDirawat(String covidDirawat) {
        this.covidDirawat = covidDirawat;
    }

    public String getCovidIsolasiDirumah() {
        return covidIsolasiDirumah;
    }

    public void setCovidIsolasiDirumah(String covidIsolasiDirumah) {
        this.covidIsolasiDirumah = covidIsolasiDirumah;
    }

    public String getCovidMeninggal() {
        return covidMeninggal;
    }

    public void setCovidMeninggal(String covidMeninggal) {
        this.covidMeninggal = covidMeninggal;
    }

    public String getCovidSembuh() {
        return covidSembuh;
    }

    public void setCovidSembuh(String covidSembuh) {
        this.covidSembuh = covidSembuh;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

}


