package com.example.controller;

import com.example.dao.ReportDao;
import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.post;

public class ReportController {

    private ReportDao reportDao;
    private ThymeleafTemplateEngine engine = new ThymeleafTemplateEngine();

    public ReportController(ReportDao reportDao) {
        this.reportDao = reportDao;

        post("/reports", (req, res) -> {
            try {
                int userId = Integer.parseInt(req.queryParams("userId"));
                int rumourId = Integer.parseInt(req.queryParams("rumourId"));
                String reportType = req.queryParams("reportType");

                reportDao.addReport(userId, rumourId, reportType);

                res.redirect("/rumours/" + rumourId);
                return null;

            } catch (Exception e) {

                Map<String, Object> model = new HashMap<>();

                if (e.getMessage() != null && e.getMessage().contains("already")) {
                    model.put("error", " You have already reported this rumour.");
                } else {
                    model.put("error", " Failed to submit report.");
                }

                model.put("rumourId", req.queryParams("rumourId"));

                return new ModelAndView(model, "report-error.html");
            }
        }, engine); 
    }
}
