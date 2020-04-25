package com.example.danianwar.pengaduanmasyarakat;

public class Entity_Tanggapan {

    String idTanggapan;
    String idPengaduan;
    String tanggapan;
    String idPetugas;

    public Entity_Tanggapan(String idTanggapan, String idPengaduan, String tanggapan, String idPetugas) {
        this.idTanggapan = idTanggapan;
        this.idPengaduan = idPengaduan;
        this.tanggapan = tanggapan;
        this.idPetugas = idPetugas;
    }

    public Entity_Tanggapan(){}

    public String getIdTanggapan() {
        return idTanggapan;
    }

    public void setIdTanggapan(String idTanggapan) {
        this.idTanggapan = idTanggapan;
    }

    public String getIdPengaduan() {
        return idPengaduan;
    }

    public void setIdPengaduan(String idPengaduan) {
        this.idPengaduan = idPengaduan;
    }

    public String getTanggapan() {
        return tanggapan;
    }

    public void setTanggapan(String tanggapan) {
        this.tanggapan = tanggapan;
    }

    public String getIdPetugas() {
        return idPetugas;
    }

    public void setIdPetugas(String idPetugas) {
        this.idPetugas = idPetugas;
    }
}
