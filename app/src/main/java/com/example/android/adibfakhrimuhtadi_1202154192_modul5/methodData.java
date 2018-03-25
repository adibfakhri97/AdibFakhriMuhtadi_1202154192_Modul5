package com.example.android.adibfakhrimuhtadi_1202154192_modul5;

public class methodData {
    //deklarasi variable
    String judul,deskripsi,priority;

    //konstruktor
    public methodData(String judul, String deskripsi, String priority){
        this.judul=judul;
        this.deskripsi=deskripsi;
        this.priority=priority;
    }

    //method setter dan getter untuk to do , description dan priority
    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
