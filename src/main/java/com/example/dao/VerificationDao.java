package com.example.dao;

import com.example.db.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VerificationDao {
    private final RumourDao rumourDao = new RumourDao();

    public void addVerification(int rumourId, int verifierId, String result, String verifiedAt) throws SQLException {
        String insert = "INSERT INTO verifications (rumour_id, verifier_id, result, verified_at) VALUES (?, ?, ?, ?)";
        try (Connection c = DB.getConnection()) {
            c.setAutoCommit(false);
            try (PreparedStatement p = c.prepareStatement(insert)) {
                p.setInt(1, rumourId);
                p.setInt(2, verifierId);
                p.setString(3, result);
                p.setString(4, verifiedAt);
                p.executeUpdate();
            }
            
            rumourDao.setVerified(rumourId, true);

            
            if ("true".equalsIgnoreCase(result)) {
                
                rumourDao.updateStatus(rumourId, "normal");
            } else if ("false".equalsIgnoreCase(result)) {
                
                rumourDao.updateStatus(rumourId, "normal");
            }

            c.commit();
        } catch (SQLException ex) {
            throw ex;
        }
    }
}
