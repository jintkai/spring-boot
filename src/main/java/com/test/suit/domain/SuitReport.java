package com.test.suit.domain;

public class SuitReport {

    private String suitName;
    private String suitKey;
    private String suitId;
    private String responseCode;
    private String responseTime;
    private String status;
    private String buildId;
    private String id;
    private String requestOrder;


    public String getSuitName() {
        return suitName;
    }

    public void setSuitName(String suitName) {
        this.suitName = suitName;
    }

    public String getSuitKey() {
        return suitKey;
    }

    public void setSuitKey(String suitKey) {
        this.suitKey = suitKey;
    }

    public String getSuitId() {
        return suitId;
    }

    public void setSuitId(String suitId) {
        this.suitId = suitId;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBuildId() {
        return buildId;
    }

    public void setBuildId(String buildId) {
        this.buildId = buildId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRequestOrder() {
        return requestOrder;
    }

    public void setRequestOrder(String requestOrder) {
        this.requestOrder = requestOrder;
    }
}
