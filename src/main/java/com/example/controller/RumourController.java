package com.example.controller;

import com.example.dao.RumourDao;
import com.example.model.Rumour;
import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class RumourController {

    private final RumourDao rumourDao = new RumourDao();

    public void routes() {

      
        get("/rumours", (req, res) -> {

            List<Rumour> rumours = rumourDao.findAll();

            Map<String, Object> model = new HashMap<>();
            model.put("rumours", rumours);

            return new ModelAndView(model, "rumours.html");
        }, new ThymeleafTemplateEngine());


        
        get("/rumours/:id", (req, res) -> {

            int rumourId;
            try {
                rumourId = Integer.parseInt(req.params(":id"));
            } catch (NumberFormatException e) {
                halt(400, "Invalid rumour ID");
                return null;
            }

            Rumour rumour = rumourDao.findById(rumourId);
            if (rumour == null) {
                halt(404, "Rumour not found");
                return null;
            }

            int reportCount = rumourDao.countReports(rumourId);

            Map<String, Object> model = new HashMap<>();
            model.put("rumour", rumour);
            model.put("reportCount", reportCount);

            return new ModelAndView(model, "rumour-detail.html");
        }, new ThymeleafTemplateEngine());

    }
}

