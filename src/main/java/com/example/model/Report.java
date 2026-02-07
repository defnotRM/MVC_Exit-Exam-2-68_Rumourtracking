package com.example.model;

public class Report {

    private int reportId;
    private int userId;
    private int rumourId;
    private String reportType;
    private String reportedAt;

    
    public Report() {
    }

    
    public Report(int reportId, int userId, int rumourId, String reportType, String reportedAt) {
        this.reportId = reportId;
        this.userId = userId;
        this.rumourId = rumourId;
        this.reportType = reportType;
        this.reportedAt = reportedAt;
    }

    
    public Report(int userId, int rumourId, String reportType, String reportedAt) {
        this.userId = userId;
        this.rumourId = rumourId;
        this.reportType = reportType;
        this.reportedAt = reportedAt;
    }

    
    public int getReportId() {
        return reportId;
    }

    public int getUserId() {
        return userId;
    }

    public int getRumourId() {
        return rumourId;
    }

    public String getReportType() {
        return reportType;
    }

    public String getReportedAt() {
        return reportedAt;
    }

    
    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setRumourId(int rumourId) {
        this.rumourId = rumourId;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public void setReportedAt(String reportedAt) {
        this.reportedAt = reportedAt;
    }

    @Override
    public String toString() {
        return "Report{" +
                "reportId=" + reportId +
                ", userId=" + userId +
                ", rumourId=" + rumourId +
                ", reportType='" + reportType + '\'' +
                ", reportedAt='" + reportedAt + '\'' +
                '}';
    }
}


