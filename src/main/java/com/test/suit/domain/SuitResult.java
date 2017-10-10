package com.test.suit.domain;

import java.util.Date;

public class SuitResult {


    private int id;
    private int suitid;
    private int buildId;
    private String suitKey;
    private String responseHeaders;
    private String responseBody;
    private String responseCode;
    private float responseTime;
    private String assertLog;
    //0,1,2:未执行，执行成功，执行失败
    private int status;
    private Date insertTime;
    private Date updateTime;


    public SuitResult() {
    }


    public SuitResult(int suitid, int buildId, String suitKey, String responseHeaders, String responseBody, String responseCode, float responseTime, String assertLog, int status) {

        this.suitid = suitid;
        this.suitKey = suitKey;
        this.buildId = buildId;
        this.suitKey = suitKey;
        this.responseHeaders = responseHeaders;
        this.responseBody = responseBody;
        this.responseCode = responseCode;
        this.responseTime = responseTime;
        this.assertLog = assertLog;
        this.status = status;
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

    public int getSuitid() {
        return suitid;
    }

    public void setSuitid(int suitid) {
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

    public float getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(float responseTime) {
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
