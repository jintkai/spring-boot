package com.test.report;

import java.util.Date;

public class SuitBuildRecord {

    private String suitKey;
    private int buildid;
    private Date inserttime;
    private int success;
    private int fail;
    private int unExecution;
    private int total;
    private float successPercent;

    public SuitBuildRecord(String suitKey, int buildid, Date insertime, int success, int fail, int unExecution, int total, float successPercent) {
        this.suitKey = suitKey;
        this.buildid = buildid;
        this.inserttime = insertime;
        this.success = success;
        this.fail = fail;
        this.unExecution = unExecution;
        this.total = total;
        this.successPercent = successPercent;
    }

    public SuitBuildRecord() {
    }

    public String getSuitKey() {
        return suitKey;
    }

    public void setSuitKey(String suitKey) {
        this.suitKey = suitKey;
    }

    public int getBuildid() {
        return buildid;
    }

    public void setBuildid(int buildid) {
        this.buildid = buildid;
    }

    public Date getInsertTime() {
        return inserttime;
    }

    public void setInsertTime(Date insertime) {
        this.inserttime = insertime;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getFail() {
        return fail;
    }

    public void setFail(int fail) {
        this.fail = fail;
    }

    public int getUnExecution() {
        return unExecution;
    }

    public void setUnExecution(int unExecution) {
        this.unExecution = unExecution;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public float getSuccessPercent() {
        return successPercent;
    }

    public void setSuccessPercent(float successPercent) {
        this.successPercent = successPercent;
    }}
