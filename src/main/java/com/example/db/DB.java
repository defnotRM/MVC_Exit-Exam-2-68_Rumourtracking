package com.example.db;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

    private static final String DB_NAME = "rumours.db";

    public static Connection getConnection() throws SQLException {
        try {
            
            Path tempDir = Path.of(System.getProperty("java.io.tmpdir"));
            Path dbPath = tempDir.resolve(DB_NAME);

            
            if (!Files.exists(dbPath)) {
                try (InputStream is = DB.class
                        .getClassLoader()
                        .getResourceAsStream(DB_NAME)) {

                    if (is == null) {
                        throw new RuntimeException("Cannot find rumours.db in resources");
                    }

                    Files.copy(is, dbPath, StandardCopyOption.REPLACE_EXISTING);
                }
            }

            String url = "jdbc:sqlite:" + dbPath.toAbsolutePath();
            System.out.println("Using SQLite DB at: " + url);

            return DriverManager.getConnection(url);

        } catch (Exception e) {
            throw new SQLException("Failed to open SQLite DB", e);
        }
    }
}
