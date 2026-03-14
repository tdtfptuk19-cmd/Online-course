package dal;

import java.sql.*;
import java.util.*;
import models.Course;

public class CourseDAO extends DBContext {
    PreparedStatement ps;
    ResultSet rs;
    public List<Course> getAllCourses() {
        List<Course> list = new ArrayList<>();
        String sql = "SELECT * FROM Course";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Course c = new Course(
                        rs.getInt("course_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getInt("price"),
                        rs.getString("image"),
                        rs.getInt("category_id"),
                        rs.getInt("enroll")
                );
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<Course> getAllPopularCourses(){
        List<Course> list = new ArrayList<>();
        String sql = "SELECT * FROM Course WHERE enroll >= 30 ORDER BY enroll DESC";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Course c = new Course(
                        rs.getInt("course_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getInt("price"),
                        rs.getString("image"),
                        rs.getInt("category_id"),
                        rs.getInt("enroll")
                );
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public Course GetCourseById(int id){
        try{
            String strSQL = "select * from Course where course_id = ?";
            ps = connection.prepareStatement(strSQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                Course c = new Course(
                        rs.getInt("course_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getInt("price"),
                        rs.getString("image"),
                        rs.getInt("category_id"),
                        rs.getInt("enroll")
                );
                return c;
            }
        }catch(Exception e){
            System.out.println("" + e.getMessage());
        }
        return null;
    }
    public List<Course> getAllMajorCourse(int categoryId){
        List<Course> list = new ArrayList<>();
        String sql = "SELECT * FROM Course WHERE category_id = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, categoryId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Course c = new Course(
                        rs.getInt("course_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getInt("price"),
                        rs.getString("image"),
                        rs.getInt("category_id"),
                        rs.getInt("enroll")
                );
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
