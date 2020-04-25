package com.example.danianwar.pengaduanmasyarakat;

public class Entity_Petugas_Admin {

    String idPetugas;
    String nama_petugas;
    String username;
    String password;
    String telp;

    public Entity_Petugas_Admin(String idPetugas, String nama_petugas, String username, String password, String telp) {
        this.idPetugas = idPetugas;
        this.nama_petugas = nama_petugas;
        this.username = username;
        this.password = password;
        this.telp = telp;
    }

    public Entity_Petugas_Admin(){}

    public String getIdPetugas() {
        return idPetugas;
    }

    public void setIdPetugas(String idPetugas) {
        this.idPetugas = idPetugas;
    }

    public String getNama_petugas() {
        return nama_petugas;
    }

    public void setNama_petugas(String nama_petugas) {
        this.nama_petugas = nama_petugas;
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
