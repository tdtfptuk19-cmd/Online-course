/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import models.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class AccountDAO extends DBContext {

    PreparedStatement stm;
    ResultSet rs;

    public List<Account> GetAllAccount() {
        List<Account> list = new ArrayList<>();
        try {
            String strSQL = "select * from Account";
            stm = connection.prepareStatement(strSQL);
            rs = stm.executeQuery();
            while (rs.next()) {
                int accountId = rs.getInt("account_id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String fullname = rs.getString("fullname");
                String email = rs.getString("email");
                Date date = rs.getDate("created_at");
                int roleId = rs.getInt("role_id");
                Account account = new Account(accountId, username, password, fullname, email, roleId, date);
                list.add(account);
            }
        } catch (Exception e) {
            System.out.println("GetAccountById:" + e.getMessage());
        }
        return list;
    }

    public boolean InsertAccout(Account a) {
        try {
            String strSQL = "INSERT INTO Account(username, password, fullname, email, role_id, created_at) VALUES (?, ?, ?, ?, ?, getDate())";
            stm = connection.prepareStatement(strSQL);
            stm.setString(1, a.getUsername());
            stm.setString(2, a.getPassword());
            stm.setNString(3, a.getFullName());
            stm.setString(4, a.getEmail());
            stm.setInt(5, 3);
            int affected = stm.executeUpdate();
            return affected > 0;
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
        return false;
    }

    public Account GetAccountByUsername(String login) {
        Account account = null;
        try {
            String strSQL = "select * from Account where username = ?";
            stm = connection.prepareStatement(strSQL);
            stm.setString(1, login);
            rs = stm.executeQuery();
            while (rs.next()) {
                int accountId = rs.getInt("account_id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String fullname = rs.getString("fullname");
                String email = rs.getString("email");
                Date date = rs.getDate("created_at");
                int roleId = rs.getInt("role_id");
                account = new Account(accountId, username, password, fullname, email, roleId, date);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return account;
    }

    public boolean deleteAccountById(int id) {
        String sql = "DELETE FROM Account WHERE account_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateAccountInfo(Account acc) {
        String sql = "UPDATE Account SET username = ?, password = ?, fullname = ?, email = ? WHERE account_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, acc.getUsername());
            ps.setString(2, acc.getPassword());
            ps.setString(3, acc.getFullName());
            ps.setString(4, acc.getEmail());
            ps.setInt(5, acc.getAccountId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Account> listUserAndStaff() {
        List<Account> list = new ArrayList<>();
        String sql = """
        SELECT a.account_id, a.username, a.password, a.fullname, a.email,
               a.role_id, a.created_at
        FROM Account a
        WHERE a.role_id IN (2,3)  -- nếu muốn thấy cả admin thì đổi thành IN (1,2,3)
        ORDER BY a.account_id ASC
    """;
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Account a = new Account();
                a.setAccountId(rs.getInt("account_id"));
                a.setUsername(rs.getString("username"));
                a.setPassword(rs.getString("password"));
                a.setFullName(rs.getString("fullname"));
                a.setEmail(rs.getString("email"));
                a.setRoleId(rs.getInt("role_id"));
                a.setCreateAt(rs.getTimestamp("created_at"));
                list.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Account> listUser() {
        List<Account> list = new ArrayList<>();
        String sql = """
        SELECT a.account_id, a.username, a.password, a.fullname, a.email,
               a.role_id, a.created_at
        FROM Account a
        WHERE a.role_id = 3
        ORDER BY a.account_id ASC
    """;
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Account a = new Account();
                a.setAccountId(rs.getInt("account_id"));
                a.setUsername(rs.getString("username"));
                a.setPassword(rs.getString("password"));
                a.setFullName(rs.getString("fullname"));
                a.setEmail(rs.getString("email"));
                a.setRoleId(rs.getInt("role_id"));
                a.setCreateAt(rs.getTimestamp("created_at"));
                list.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Account getById(int id) {
        Account account = null;
        String sql = """
        SELECT a.account_id, a.username, a.password, a.fullname, a.email,
               a.role_id, a.created_at
        FROM Account a
        WHERE a.account_id = ?
    """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    account = new Account();
                    account.setAccountId(rs.getInt("account_id"));
                    account.setUsername(rs.getString("username"));
                    account.setPassword(rs.getString("password"));
                    account.setFullName(rs.getString("fullname"));
                    account.setEmail(rs.getString("email"));
                    account.setRoleId(rs.getInt("role_id"));
                    account.setCreateAt(rs.getTimestamp("created_at"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }
}
