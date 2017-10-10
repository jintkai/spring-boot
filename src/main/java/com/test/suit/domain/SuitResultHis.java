package com.test.suit.domain;

import java.util.Date;

public class SuitResultHis {
    private int id;

    private int buildid;

    private int suitid;

    private String suitKey;

    private String requestinfo;

    private String responseheaders;

    private String responsecode;

    private String responsebody;

    private Date inserttime;

    private float responsetime;

    private String assertlog;

    //0,1,2:未执行，执行成功，执行失败
    private int status;

    public SuitResultHis() {
    }

    public SuitResultHis(int id, int buildid, int suitid, String suitKey, String requestinfo, String responseheaders, String responsecode, String responsebody, Date inserttime, Float responsetime, String assertlog, int status) {
        this.id = id;
        this.buildid = buildid;
        this.suitid = suitid;
        this.suitKey = suitKey;
        this.requestinfo = requestinfo;
        this.responseheaders = responseheaders;
        this.responsecode = responsecode;
        this.responsebody = responsebody;
        this.inserttime = inserttime;
        this.responsetime = responsetime;
        this.assertlog = assertlog;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBuildid() {
        return buildid;
    }

    public void setBuildid(int buildid) {
        this.buildid = buildid;
    }

    public int getSuitid() {
        return suitid;
    }

    public void setSuitid(int suitid) {
        this.suitid = suitid;
    }

    public String getSuitKey() {
        return suitKey;
    }

    public void setSuitKey(String suitKey) {
        this.suitKey = suitKey;
    }

    public String getRequestinfo() {
        return requestinfo;
    }

    public void setRequestinfo(String requestinfo) {
        this.requestinfo = requestinfo == null ? null : requestinfo.trim();
    }

    public String getResponseheaders() {
        return responseheaders;
    }

    public void setResponseheaders(String responseheaders) {
        this.responseheaders = responseheaders == null ? null : responseheaders.trim();
    }

    public String getResponsecode() {
        return responsecode;
    }

    public void setResponsecode(String responsecode) {
        this.responsecode = responsecode == null ? null : responsecode.trim();
    }

    public String getResponsebody() {
        return responsebody;
    }

    public void setResponsebody(String responsebody) {
        this.responsebody = responsebody == null ? null : responsebody.trim();
    }

    public Date getInserttime() {
        return inserttime;
    }

    public void setInserttime(Date inserttime) {
        this.inserttime = inserttime;
    }

    public float getResponsetime() {
        return responsetime;
    }

    public void setResponsetime(Float responsetime) {
        this.responsetime = responsetime;
    }

    public String getAssertlog() {
        return assertlog;
    }

    public void setAssertlog(String assertlog) {
        this.assertlog = assertlog == null ? null : assertlog.trim();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}