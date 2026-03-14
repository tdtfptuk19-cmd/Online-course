package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Enrollment;

public class EnrollmentDAO extends DBContext {

    // Thêm bản ghi đăng ký khóa học
    public boolean addEnrollment(int accountId, int courseId) {
        String sql = "INSERT INTO Enrollment (AccountID, CourseID) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, accountId);
            ps.setInt(2, courseId);
            int rows = ps.executeUpdate();
            return rows > 0; // true nếu chèn thành công
        } catch (SQLException e) {
            // Nếu trùng UNIQUE(AccountID,CourseID) thì tức là đã đăng ký
            return false;
        }
    }

    // Kiểm tra xem người dùng đã đăng ký khóa học chưa
    public boolean isEnrolled(int accountId, int courseId) {
        String sql = "SELECT 1 FROM Enrollment WHERE AccountID = ? AND CourseID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, accountId);
            ps.setInt(2, courseId);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // true nếu có bản ghi
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lấy danh sách các khóa học mà user đã đăng ký
    public List<Enrollment> getEnrollmentsByAccount(int accountId) {
        List<Enrollment> list = new ArrayList<>();
        String sql = "SELECT * FROM Enrollment WHERE AccountID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Enrollment e = new Enrollment(
                    rs.getInt("EnrollmentID"),
                    rs.getInt("AccountID"),
                    rs.getInt("CourseID"),
                    rs.getDate("EnrollmentDate")
                );
                list.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
