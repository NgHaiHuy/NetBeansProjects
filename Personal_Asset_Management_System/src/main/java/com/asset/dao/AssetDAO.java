/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.asset.dao;
import com.asset.util.DatabaseConnection;
import java.sql.*;

/**
 *
 * @author Nguye
 */
public class AssetDAO {
// 1. Sửa lỗi insertTaiSan: Bỏ cột không tồn tại, thêm mặc định TrangThai
    public boolean insertTaiSan(String ten, int maNhom, int maViTri, double gia) {
        // Bỏ GiaTriHienTai vì DB không có cột này. Thêm TrangThai để tránh bị NULL nếu cần.
        String sql = "INSERT INTO TaiSan (TenTaiSan, MaNhom, MaViTri, NgayMua, GiaMua, TrangThai) VALUES (?, ?, ?, GETDATE(), ?, N'Đang sử dụng')";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, ten);
            pst.setInt(2, maNhom);
            pst.setInt(3, maViTri);
            pst.setDouble(4, gia);
            
            return pst.executeUpdate() > 0;
        } catch (Exception e) { 
            System.out.println("Lỗi Insert Tài Sản: " + e.getMessage());
            e.printStackTrace(); 
            return false; 
        }
    }

    // 2. Sửa lỗi muonTaiSan: Khớp tên cột với Database (NgayTra)
    public boolean muonTaiSan(int maTS, String nguoiMuon, String ngayHen) {
        // Tên cột đúng trong DB của bạn là NgayTra, không phải NgayHenTra
        String sql = "INSERT INTO GiaoDichChoMuon (MaTaiSan, NguoiMuon, NgayTra) VALUES (?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, maTS);
            pst.setString(2, nguoiMuon);
            // SQL Server tự hiểu format YYYY-MM-DD nếu truyền vào String đúng chuẩn
            pst.setString(3, ngayHen); 
            
            return pst.executeUpdate() > 0;
        } catch (Exception e) { 
            System.out.println("Lỗi Mượn Tài Sản: " + e.getMessage());
            e.printStackTrace(); 
            return false; 
        }
    }
}
