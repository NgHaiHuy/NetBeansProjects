/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.asset.model;

/**
 *
 * @author Nguye
 */
public class Models {
    public static class TaiSan {
        public int maTaiSan;
        public String tenTaiSan;
        public int maNhom;
        public int maViTri;
        public double giaMua;
        public String trangThai;
    }

    public static class GiaoDich {
        public int maGiaoDich;
        public int maTaiSan;
        public String nguoiMuon;
        public String ngayHenTra;
    }
}
