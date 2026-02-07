package com.example.model;

public class Verification {

    private int rumourId;
    private int verifierId;
    private String result; 
    private String verifiedAt;

    public Verification() {}

    public Verification(int rumourId, int verifierId, String result, String verifiedAt) {
        this.rumourId = rumourId;
        this.verifierId = verifierId;
        this.result = result;
        this.verifiedAt = verifiedAt;
    }

    
    public int getRumourId() {
        return rumourId;
    }

    public int getVerifierId() {
        return verifierId;
    }

    public String getResult() {
        return result;
    }

    public String getVerifiedAt() {
        return verifiedAt;
    }

    
    public void setRumourId(int rumourId) {
        this.rumourId = rumourId;
    }

    public void setVerifierId(int verifierId) {
        this.verifierId = verifierId;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setVerifiedAt(String verifiedAt) {
        this.verifiedAt = verifiedAt;
    }
}


