package com.example.model;


public class Rumour {
    private int rumourId;
    private String title;
    private String source;
    private String createdAt;
    private int credibilityScore;
    private String status; 
    private boolean isVerified;

    public Rumour() {}
    public Rumour(int rumourId, String title, String source, String createdAt,
                  int credibilityScore, String status, boolean isVerified) {
        this.rumourId = rumourId; this.title = title; this.source = source;
        this.createdAt = createdAt; this.credibilityScore = credibilityScore;
        this.status = status; this.isVerified = isVerified;
    }
    
    public int getRumourId() { return rumourId; }
    public void setRumourId(int rumourId) { this.rumourId = rumourId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    public int getCredibilityScore() { return credibilityScore; }
    public void setCredibilityScore(int credibilityScore) { this.credibilityScore = credibilityScore; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public boolean isVerified() { return isVerified; }
    public void setVerified(boolean verified) { isVerified = verified; }
}
