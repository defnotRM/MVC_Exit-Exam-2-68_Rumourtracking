package com.example.dao;

import com.example.db.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportDao {

   
    public void addReport(int userId, int rumourId, String reportType) throws Exception {

        String checkVerifiedSql =
                "SELECT is_verified FROM rumours WHERE rumour_id = ?";

        String insertSql =
                "INSERT INTO reports (user_id, rumour_id, report_type, reported_at) " +
                "VALUES (?, ?, ?, datetime('now'))";

        String countSql =
                "SELECT COUNT(*) FROM reports WHERE rumour_id = ?";

        String updatePanicSql =
                "UPDATE rumours SET status = 'panic' WHERE rumour_id = ?";

        try (Connection conn = DB.getConnection()) {
            conn.setAutoCommit(false);

            
            try (PreparedStatement ps = conn.prepareStatement(checkVerifiedSql)) {
                ps.setInt(1, rumourId);
                ResultSet rs = ps.executeQuery();

                if (!rs.next()) {
                    throw new Exception("Rumour not found");
                }

                if (rs.getInt("is_verified") == 1) {
                    throw new Exception("This rumour has already been verified");
                }
            }

            
            try (PreparedStatement ps = conn.prepareStatement(insertSql)) {
                ps.setInt(1, userId);
                ps.setInt(2, rumourId);
                ps.setString(3, reportType);
                ps.executeUpdate();
            }

            
            int count = 0;
            try (PreparedStatement ps = conn.prepareStatement(countSql)) {
                ps.setInt(1, rumourId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }

            
            if (count >= 3) {
                try (PreparedStatement ps = conn.prepareStatement(updatePanicSql)) {
                    ps.setInt(1, rumourId);
                    ps.executeUpdate();
                }
            }

            conn.commit();

        } catch (SQLException e) {
            
            if (e.getMessage().contains("FOREIGN KEY")) {
                throw new Exception("User ID does not exist");
            }
            if (e.getMessage().contains("UNIQUE")) {
                throw new Exception("You have already reported this rumour");
            }
            throw e;
        }
    }


    
    public int countByRumourId(int rumourId) {

        String sql = "SELECT COUNT(*) FROM reports WHERE rumour_id = ?";

        try (Connection conn = DB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, rumourId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Cannot count reports", e);
        }

        return 0;
    }

    
    public boolean hasUserReported(int userId, int rumourId) {

        String sql = """
            SELECT 1 FROM reports
            WHERE user_id = ? AND rumour_id = ?
        """;

        try (Connection conn = DB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, rumourId);

            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException("Cannot check report existence", e);
        }
    }
}

