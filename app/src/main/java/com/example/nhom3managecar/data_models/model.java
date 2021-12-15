package com.example.nhom3managecar.data_models;

public class model {
    String tenKh, sdt, maXe, tenXe, nhomHang,soLuong,giaBan,ngayXuat,ngayHetBh;

    public model() {
    }

    public model(String tenKh, String sdt, String maXe, String tenXe, String nhomHang, String soLuong, String giaBan, String ngayXuat, String ngayHetBh) {
        this.tenKh = tenKh;
        this.sdt = sdt;
        this.maXe = maXe;
        this.tenXe = tenXe;
        this.nhomHang = nhomHang;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.ngayXuat = ngayXuat;
        this.ngayHetBh = ngayHetBh;
    }

    public String getTenKh() {
        return tenKh;
    }

    public void setTenKh(String tenKh) {
        this.tenKh = tenKh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getMaXe() {
        return maXe;
    }

    public void setMaXe(String maXe) {
        this.maXe = maXe;
    }

    public String getTenXe() {
        return tenXe;
    }

    public void setTenXe(String tenXe) {
        this.tenXe = tenXe;
    }

    public String getNhomHang() {
        return nhomHang;
    }

    public void setNhomHang(String nhomHang) {
        this.nhomHang = nhomHang;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(String giaBan) {
        this.giaBan = giaBan;
    }

    public String getNgayXuat() {
        return ngayXuat;
    }

    public void setNgayXuat(String ngayXuat) {
        this.ngayXuat = ngayXuat;
    }

    public String getNgayHetBh() {
        return ngayHetBh;
    }

    public void setNgayHetBh(String ngayHetBh) {
        this.ngayHetBh = ngayHetBh;
    }
}
