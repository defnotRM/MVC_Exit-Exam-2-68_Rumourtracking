package com.example.controller;


import com.example.dao.RumourDao;
import com.example.db.DB;
import com.example.model.Rumour;
import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import static spark.Spark.*;

public class SummaryController {

    private final RumourDao rumourDao = new RumourDao();

    public void routes() {

        
        get("/summary", (req, res) -> {

            Map<String, Object> model = new HashMap<>();

            
            List<Rumour> panicRumours = new ArrayList<>();
            for (Rumour r : rumourDao.findAll()) {
                if ("panic".equals(r.getStatus())) {
                    panicRumours.add(r);
                }
            }

            model.put("panicRumours", panicRumours);

            
            List<Map<String, Object>> verifiedRumours = new ArrayList<>();

            String sql = """
                SELECT r.rumour_id, r.title, v.result
                FROM rumours r
                JOIN verifications v ON r.rumour_id = v.rumour_id
            """;

            try (Connection c = DB.getConnection();
                 PreparedStatement p = c.prepareStatement(sql);
                 ResultSet rs = p.executeQuery()) {

                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    row.put("rumourId", rs.getInt("rumour_id"));
                    row.put("title", rs.getString("title"));
                    row.put("result", rs.getString("result"));
                    verifiedRumours.add(row);
                }
            }

            model.put("verifiedRumours", verifiedRumours);

            return new ModelAndView(model, "summary.html");
        }, new ThymeleafTemplateEngine());
    }
}

