package com.test.caseassert.domain;

import com.test.caseassert.domain.AssertExp;

public class AssertResult {

    private AssertExp exp;
    private boolean success;
    private String log;
    //private boolean result;

    public AssertResult(AssertExp exp, boolean success) {
        this.exp = exp;
        this.success = success;
    }

    public AssertResult() {
    }


    public AssertResult(AssertExp exp, boolean success, String log) {
        this.exp = exp;
        this.success = success;
        this.log = log;
    }

    public AssertExp getExp() {
        return exp;
    }

    public void setExp(AssertExp exp) {
        this.exp = exp;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getLog() {
        return log;
    }


}
