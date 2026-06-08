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

    public Map<Integer, Category> getAllCategories() {
        Map<Integer, Category> listC = new HashMap<>();
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
            System.out.println(e.getMessage());
        }
        return listC;
    }
}
