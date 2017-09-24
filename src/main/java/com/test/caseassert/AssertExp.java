package com.test.caseassert;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AssertExp {

    @JsonProperty
    private String methodName;
    @JsonProperty
    private String params;

    public AssertExp(String methodName, String params) {
        this.methodName = methodName;
        this.params = params;
    }

    public AssertExp() {
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
