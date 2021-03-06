package com.example.nhom3managecar.data_models;

import java.util.HashMap;
import java.util.Map;

public class ModelCar {
    String maXe,tenXe,nhomXe,giaBan,giaVon,tonKho;

    String turl;

    public ModelCar() {
    }

    public ModelCar(String maXe, String tenXe, String nhomXe, String giaBan, String giaVon, String tonKho, String turl) {
        this.maXe = maXe;
        this.tenXe = tenXe;
        this.nhomXe = nhomXe;
        this.giaBan = giaBan;
        this.giaVon = giaVon;
        this.tonKho = tonKho;
        this.turl = turl;
    }

    public ModelCar(String maXe) {
        this.maXe = maXe;
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

    public String getNhomXe() {
        return nhomXe;
    }

    public void setNhomXe(String nhomXe) {
        this.nhomXe = nhomXe;
    }

    public String getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(String giaBan) {
        this.giaBan = giaBan;
    }

    public String getGiaVon() {
        return giaVon;
    }

    public void setGiaVon(String giaVon) {
        this.giaVon = giaVon;
    }

    public String getTonKho() {
        return tonKho;
    }

    public void setTonKho(String tonKho) {
        this.tonKho = tonKho;
    }

    public String getTurl() {
        return turl;
    }

    public void setTurl(String turl) {
        this.turl = turl;
    }


    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("maXe", maXe);
//        result.put("author", author);
//        result.put("title", title);
//        result.put("body", body);
//        result.put("starCount", starCount);
//        result.put("stars", stars);

        return result;
    }

}
