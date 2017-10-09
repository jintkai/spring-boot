package com.test.suit.domain;

import java.util.Date;

public class SuitResult {

    private String suitid;
    private String suitKey;
    private int buildId;
    private int id;
    private String requestInfo;
    private String responseHeaders;
    private String responseBody;
    private String responseCode;
    private int responseTime;
    private String assertLog;
    private int status;
    private Date insertTime;

    public SuitResult() {
    }

    public SuitResult(String suitid, String suitKey,int buildId, String requestInfo, String responseHeaders, String responseBody, String responseCode,
                      int responseTime) {
        this.suitid = suitid;
        this.suitKey = suitKey;
        this.buildId = buildId;
        this.requestInfo = requestInfo;
        this.responseHeaders = responseHeaders;
        this.responseBody = responseBody;
        this.responseCode = responseCode;
        this.responseTime = responseTime;
        this.assertLog = assertLog;
        this.status = status;
        this.insertTime = insertTime;
    }

    public String getSuitKey() {
        return suitKey;
    }

    public void setSuitKey(String suitKey) {
        this.suitKey = suitKey;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getSuitid() {
        return suitid;
    }

    public void setSuitid(String suitid) {
        this.suitid = suitid;
    }

    public int getBuildId() {
        return buildId;
    }

    public void setBuildId(int buildId) {
        this.buildId = buildId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRequestInfo() {
        return requestInfo;
    }

    public void setRequestInfo(String requestInfo) {
        this.requestInfo = requestInfo;
    }

    public String getResponseHeaders() {
        return responseHeaders;
    }

    public void setResponseHeaders(String responseHeader) {
        this.responseHeaders = responseHeader;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public int getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }

    public String getAssertLog() {
        return assertLog;
    }

    public void setAssertLog(String assertLog) {
        this.assertLog = assertLog;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
}
