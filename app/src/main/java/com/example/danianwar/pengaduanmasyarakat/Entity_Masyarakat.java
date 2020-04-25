package com.example.danianwar.pengaduanmasyarakat;

public class Entity_Masyarakat {

    String idMasyarakat;
    String nik;
    String nama_masyarakat;
    String username;
    String password;
    String telp;

    public Entity_Masyarakat(String idMasyarakat, String nik, String nama_masyarakat, String username, String password, String telp) {
        this.idMasyarakat = idMasyarakat;
        this.nik = nik;
        this.nama_masyarakat = nama_masyarakat;
        this.username = username;
        this.password = password;
        this.telp = telp;
    }

    public Entity_Masyarakat(){}

    public String getIdMasyarakat() {
        return idMasyarakat;
    }

    public void setIdMasyarakat(String idMasyarakat) {
        this.idMasyarakat = idMasyarakat;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama_masyarakat() {
        return nama_masyarakat;
    }

    public void setNama_masyarakat(String nama_masyarakat) {
        this.nama_masyarakat = nama_masyarakat;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }
}
