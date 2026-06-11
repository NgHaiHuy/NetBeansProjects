/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import java.util.HashMap;
import java.util.Map;
import model.Category;

/**
 *
 * @author LECOO
 */
public class CategoryDAO extends DBContext {

    private String lastError = null;

    public Map<Integer, Category> getAllCategories() {
        Map<Integer, Category> listC = new HashMap<>();
        if (connection == null) {
            lastError = "Connection object is null! DBContext failed to connect to the database.";
            return listC;
        }
        try {
            String sql = "select * from Categories";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Category c = new Category();
                c.setId(rs.getInt("ID"));
                c.setName(rs.getString("Name"));
                listC.put(c.getId(), c);
            }
            rs.close();
            st.close();

        } catch (Exception e) {
            lastError = (e.getMessage() != null) ? e.getMessage() : e.toString();
            System.out.println(e.getMessage());
        }
        return listC;
    }

    public String getLastError() {
        return lastError;
    }

    public java.util.List<String> getAllTableNames() {
        java.util.List<String> tables = new java.util.ArrayList<>();
        try {
            java.sql.DatabaseMetaData meta = connection.getMetaData();
            ResultSet rs = meta.getTables(null, null, "%", new String[]{"TABLE"});
            while (rs.next()) {
                tables.add(rs.getString("TABLE_NAME"));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return tables;
    }
}
