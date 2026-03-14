/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Category;

/**
 *
 * @author Admin
 */
public class CategoryDAO extends DBContext{
    PreparedStatement ps;
    ResultSet rs;
    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM Category";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Category c = new Category(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description")
                );
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public Category GetCategoryById(int id){
        try{
            String strSQL = "select * from Category where id = ?";
            ps = connection.prepareStatement(strSQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                Category c = new Category(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description")
                );
                return c;
            }
        }catch(Exception e){
            System.out.println("" + e.getMessage());
        }
        return null;
    }
}
