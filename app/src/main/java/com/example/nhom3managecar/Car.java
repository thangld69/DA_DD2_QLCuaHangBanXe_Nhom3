package com.example.nhom3managecar;

public class Car {
    private String maXe,tenXe,loaiXe,giaXe,soLuong;
    private String imageURL;

    public Car() {
    }

    public Car(String maXe, String tenXe, String loaiXe, String giaXe, String soLuong, String imageURL) {
        this.maXe = maXe;
        this.tenXe = tenXe;
        this.loaiXe = loaiXe;
        this.giaXe = giaXe;
        this.soLuong = soLuong;
        this.imageURL = imageURL;
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

    public String getLoaiXe() {
        return loaiXe;
    }

    public void setLoaiXe(String loaiXe) {
        this.loaiXe = loaiXe;
    }

    public String getGiaXe() {
        return giaXe;
    }

    public void setGiaXe(String giaXe) {
        this.giaXe = giaXe;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
