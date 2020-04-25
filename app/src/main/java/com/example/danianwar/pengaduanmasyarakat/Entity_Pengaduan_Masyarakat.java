package com.example.danianwar.pengaduanmasyarakat;

public class Entity_Pengaduan_Masyarakat {

    String idPengaduan;
    String nik;
    String isi_laporan;
    String foto;

    public Entity_Pengaduan_Masyarakat(String idPengaduan, String nik, String isi_laporan, String foto) {
        this.idPengaduan = idPengaduan;
        this.nik = nik;
        this.isi_laporan = isi_laporan;
        this.foto = foto;
    }

    public Entity_Pengaduan_Masyarakat(){}

    public String getIdPengaduan() {
        return idPengaduan;
    }

    public void setIdPengaduan(String idPengaduan) {
        this.idPengaduan = idPengaduan;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getIsi_laporan() {
        return isi_laporan;
    }

    public void setIsi_laporan(String isi_laporan) {
        this.isi_laporan = isi_laporan;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
