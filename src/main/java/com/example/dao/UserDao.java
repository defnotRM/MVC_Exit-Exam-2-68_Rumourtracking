package com.example.dao;

import com.example.db.DB;
import com.example.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public void createUser(User u) throws SQLException {
        String sql = "INSERT INTO users(user_id, name, role) VALUES (?, ?, ?);";
        try (Connection c = DB.getConnection();
             PreparedStatement p = c.prepareStatement(sql)) {
            p.setInt(1, u.getUserId());
            p.setString(2, u.getName());
            p.setString(3, u.getRole());
            p.executeUpdate();
        }
    }

    public User findById(int id) throws SQLException {
        String sql = "SELECT user_id, name, role FROM users WHERE user_id = ?";
        try (Connection c = DB.getConnection();
             PreparedStatement p = c.prepareStatement(sql)) {
            p.setInt(1, id);
            try (ResultSet rs = p.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getInt("user_id"), rs.getString("name"), rs.getString("role"));
                }
                return null;
            }
        }
    }

    public List<User> findAll() throws SQLException {
        String sql = "SELECT user_id, name, role FROM users";
        List<User> out = new ArrayList<>();
        try (Connection c = DB.getConnection();
             PreparedStatement p = c.prepareStatement(sql);
             ResultSet rs = p.executeQuery()) {
            while (rs.next()) {
                out.add(new User(rs.getInt("user_id"), rs.getString("name"), rs.getString("role")));
            }
        }
        return out;
    }
}
