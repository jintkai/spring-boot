package com.test.suit.domain;

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
    private int buildId;

    public CaseSuit() {
    }

    public CaseSuit(String id, String suitKey, String suitName, String caseId, int requestType, String requestUrl, String requestHeader, String requestParamets, String requestDependent, int requestOrder, int caseResult, String assertExp, int buildId) {
        this.id = id;
        this.suitKey = suitKey;
        this.suitName = suitName;
        this.caseId = caseId;
        this.requestType = requestType;
        this.requestUrl = requestUrl;
        this.requestHeader = requestHeader;
        this.requestParamets = requestParamets;
        this.requestDependent = requestDependent;
        this.requestOrder = requestOrder;
        this.caseResult = caseResult;
        this.assertExp = assertExp;
        this.buildId = buildId;
    }

    public int getBuildId() {
        return buildId;
    }

    public void setBuildId(int buildId) {
        this.buildId = buildId;
    }

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
