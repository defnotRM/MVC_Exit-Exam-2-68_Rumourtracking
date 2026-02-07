package com.example;

import static spark.Spark.*;

import com.example.controller.RumourController;
import com.example.controller.ReportController;
import com.example.controller.SummaryController;
import com.example.dao.ReportDao;

public class App {

    public static void main(String[] args) {

        port(4567);
        staticFiles.location("/public");

        
        new RumourController().routes();
        new SummaryController().routes();

        
        ReportDao reportDao = new ReportDao();
        new ReportController(reportDao);

        get("/", (req, res) -> {
            res.redirect("/rumours");
            return null;
        });

        exception(Exception.class, (e, req, res) -> {
            e.printStackTrace();
        });

        System.out.println("Rumour Tracking System started at http://localhost:4567");
    }
}
