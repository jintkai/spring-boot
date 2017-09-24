package com.test.suit;

public class SuitResult {

    private String suitGuid;
    private String requestParameter;
    private String status;
    private String resultBody;
    private String responseTime;
    private String assertLog;
    private int resultStatus;

    public SuitResult(String suitGuid, String requestParameter, String status, String resultBody, String responseTime, String assertLog, int resultStatus) {
        this.suitGuid = suitGuid;
        this.requestParameter = requestParameter;
        this.status = status;
        this.resultBody = resultBody;
        this.responseTime = responseTime;
        this.assertLog = assertLog;
        this.resultStatus = resultStatus;
    }

    public String getAssertLog() {
        return assertLog;
    }

    public void setAssertLog(String assertLog) {
        this.assertLog = assertLog;
    }

    public int getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(int resultStatus) {
        this.resultStatus = resultStatus;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    public SuitResult() {
    }

    public String getSuitGuid() {
        return suitGuid;
    }

    public void setSuitGuid(String suitGuid) {
        this.suitGuid = suitGuid;
    }

    public String getRequestParameter() {
        return requestParameter;
    }

    public void setRequestParameter(String requestParameter) {
        this.requestParameter = requestParameter;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResultBody() {
        return resultBody;
    }

    public void setResultBody(String resultBody) {
        this.resultBody = resultBody;
    }
}
