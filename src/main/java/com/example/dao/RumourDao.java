package com.example.dao;

import com.example.db.DB;
import com.example.model.Rumour;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RumourDao {
    public void createRumour(Rumour r) throws SQLException {
        String sql = "INSERT INTO rumours (rumour_id, title, source, created_at, credibility_score, status, is_verified) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection c = DB.getConnection();
             PreparedStatement p = c.prepareStatement(sql)) {
            p.setInt(1, r.getRumourId());
            p.setString(2, r.getTitle());
            p.setString(3, r.getSource());
            p.setString(4, r.getCreatedAt());
            p.setInt(5, r.getCredibilityScore());
            p.setString(6, r.getStatus());
            p.setInt(7, r.isVerified() ? 1 : 0);
            p.executeUpdate();
        }
    }

    public Rumour findById(int rumourId) throws SQLException {
        String sql = "SELECT * FROM rumours WHERE rumour_id = ?";
        try (Connection c = DB.getConnection();
             PreparedStatement p = c.prepareStatement(sql)) {
            p.setInt(1, rumourId);
            try (ResultSet rs = p.executeQuery()) {
                if (rs.next()) {
                    return new Rumour(
                        rs.getInt("rumour_id"),
                        rs.getString("title"),
                        rs.getString("source"),
                        rs.getString("created_at"),
                        rs.getInt("credibility_score"),
                        rs.getString("status"),
                        rs.getInt("is_verified") == 1
                    );
                }
                return null;
            }
        }
    }

    public List<Rumour> findAll() throws SQLException {
        String sql = "SELECT * FROM rumours";
        List<Rumour> out = new ArrayList<>();
        try (Connection c = DB.getConnection();
             PreparedStatement p = c.prepareStatement(sql);
             ResultSet rs = p.executeQuery()) {
            while (rs.next()) {
                out.add(new Rumour(
                    rs.getInt("rumour_id"),
                    rs.getString("title"),
                    rs.getString("source"),
                    rs.getString("created_at"),
                    rs.getInt("credibility_score"),
                    rs.getString("status"),
                    rs.getInt("is_verified") == 1
                ));
            }
        }
        return out;
    }

    public void updateStatus(int rumourId, String newStatus) throws SQLException {
        String sql = "UPDATE rumours SET status = ? WHERE rumour_id = ?";
        try (Connection c = DB.getConnection();
             PreparedStatement p = c.prepareStatement(sql)) {
            p.setString(1, newStatus);
            p.setInt(2, rumourId);
            p.executeUpdate();
        }
    }

    public void setVerified(int rumourId, boolean verified) throws SQLException {
        String sql = "UPDATE rumours SET is_verified = ? WHERE rumour_id = ?";
        try (Connection c = DB.getConnection();
             PreparedStatement p = c.prepareStatement(sql)) {
            p.setInt(1, verified ? 1 : 0);
            p.setInt(2, rumourId);
            p.executeUpdate();
        }
    }

    public int countReports(int rumourId) throws SQLException {
        String sql = "SELECT COUNT(*) AS cnt FROM reports WHERE rumour_id = ?";
        try (Connection c = DB.getConnection();
             PreparedStatement p = c.prepareStatement(sql)) {
            p.setInt(1, rumourId);
            try (ResultSet rs = p.executeQuery()) {
                return rs.next() ? rs.getInt("cnt") : 0;
            }
        }
    }
}
