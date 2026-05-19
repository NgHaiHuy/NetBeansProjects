/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.asset.view;
import com.asset.dao.AssetDAO;
import java.util.Scanner;

/**
 *
 * @author Nguye
 */
public class MainApp {
    private static AssetDAO dao = new AssetDAO(); //Data Access Object
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- PERSONAL ASSET MANAGEMENT ---");
            System.out.println("1. Them Tai San");
            System.out.println("2. Cho Muon Tai San");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");
            
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    System.out.print("Ten tai san: "); String ten = sc.nextLine();
                    System.out.print("Ma Nhom: "); int nhom = Integer.parseInt(sc.nextLine());
                    System.out.print("Ma Vi Tri: "); int vt = Integer.parseInt(sc.nextLine());
                    System.out.print("Gia: "); double gia = Double.parseDouble(sc.nextLine());
                    if(dao.insertTaiSan(ten, nhom, vt, gia)) System.out.println("Thanh cong!");
                    break;
                case 2:
                    System.out.print("Ma TS: "); int maTS = Integer.parseInt(sc.nextLine());
                    System.out.print("Nguoi muon: "); String nguoi = sc.nextLine();
                    System.out.print("Ngay hen tra (YYYY-MM-DD): "); String ngay = sc.nextLine();
                    if(dao.muonTaiSan(maTS, nguoi, ngay)) System.out.println("Đa ghi nhan muon!");
                    break;
                case 0: System.exit(0);
            }
        }
    }
}
