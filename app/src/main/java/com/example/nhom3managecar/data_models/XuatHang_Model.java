package com.example.nhom3managecar.data_models;

import java.util.HashMap;
import java.util.Map;

public class XuatHang_Model {
    String tenKh, Sdt, maXe, tenXe, nhomHang,soLuong,giaBan,ngayXuat,NgayHetBh;

    public XuatHang_Model() {
    }

    public XuatHang_Model(String tenKh, String sdt, String maXe, String tenXe, String nhomHang, String soLuong, String giaBan, String ngayXuat, String ngayHetBh) {
        this.tenKh = tenKh;
        Sdt = sdt;
        this.maXe = maXe;
        this.tenXe = tenXe;
        this.nhomHang = nhomHang;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.ngayXuat = ngayXuat;
        NgayHetBh = ngayHetBh;
    }

    public String getTenKh() {
        return tenKh;
    }

    public void setTenKh(String tenKh) {
        this.tenKh = tenKh;
    }

    public String getSdt() {
        return Sdt;
    }

    public void setSdt(String sdt) {
        Sdt = sdt;
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
        return NgayHetBh;
    }

    public void setNgayHetBh(String ngayHetBh) {
        NgayHetBh = ngayHetBh;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("tenKh", tenKh);
        result.put("sdt", Sdt);
        result.put("maXe", maXe);
        result.put("tenXe", tenXe);
        result.put("nhomHang", nhomHang);
        result.put("soLuong", soLuong);
        result.put("giaBan", giaBan);
        result.put("ngayXuat", ngayXuat);
        result.put("ngayHetBh", NgayHetBh);

        return result;
    }
}
