package com.test.suit;

public class SuitResult {

    private String suitGuid;
    private String requestParameter;
    private String status;
    private String resultBody;
    private String responseTime;

    public SuitResult(String suitGuid, String requestParameter, String status, String resultBody, String responseTime) {
        this.suitGuid = suitGuid;
        this.requestParameter = requestParameter;
        this.status = status;
        this.resultBody = resultBody;
        this.responseTime = responseTime;
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
