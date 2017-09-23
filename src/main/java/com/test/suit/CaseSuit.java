package com.test.suit;

public class CaseSuit {

    private String id;
    private String suitKey;
    private String suitName;
    private String caseId;
    private int requestType;
    private String requestUrl;
    private String requestHeader;
    private String requestParamets;
    private String requestDependent;
    private int requestOrder;
    private int caseResult;
    private String assertExp;

    public String getSuitKey() {
        return suitKey;
    }

    public void setSuitKey(String suitKey) {
        this.suitKey = suitKey;
    }

    public String getSuitName() {
        return suitName;
    }

    public void setSuitName(String suitName) {
        this.suitName = suitName;
    }

    public String getAssertExp() {
        return assertExp;
    }

    public void setAssertExp(String assertExp) {
        this.assertExp = assertExp;
    }

    public int getCaseResult() {
        return caseResult;
    }

    public void setCaseResult(int caseResult) {
        this.caseResult = caseResult;
    }

    public int getRequestOrder() {
        return requestOrder;
    }

    public void setRequestOrder(int requestOrder) {
        this.requestOrder = requestOrder;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public int getRequestType() {
        return requestType;
    }

    public void setRequestType(int requestType) {
        this.requestType = requestType;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader(String requestHeader) {
        this.requestHeader = requestHeader;
    }

    public String getRequestParamets() {
        return requestParamets;
    }

    public void setRequestParamets(String requestParamets) {
        this.requestParamets = requestParamets;
    }

    public String getRequestDependent() {
        return requestDependent;
    }

    public void setRequestDependent(String requestDependent) {
        this.requestDependent = requestDependent;
    }
}
