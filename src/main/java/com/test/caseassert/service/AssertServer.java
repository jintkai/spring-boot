package com.test.caseassert.service;

import com.test.caseassert.domain.AssertExp;
import com.test.caseassert.domain.AssertResult;
import com.test.suit.domain.SuitResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssertServer {



    private Map<String,Object> map = new HashMap<String,Object>();

    private List<AssertExp> assertExpList;
    private List<AssertResult> assertResults = new ArrayList<AssertResult>();
    private boolean success = true;
    private SuitResult result;

    private Map<String,Object> resultMap = new HashMap<String,Object>();

    public AssertServer(List<AssertExp> list, SuitResult result) {
        this.assertExpList = list;
        this.result = result;
    }

    public AssertServer() {
    }

    public Map<String,Object> assertResult(){



        for (int i = 0; i<assertExpList.size(); i++){
            AssertExp assertExp = assertExpList.get(i);
            String methodName = assertExp.getMethodName();
            switch (methodName){
                case "ResponseCodeEqual" :
                    responseCodeEqual(assertExp);
                    break;
                case "ResponseCodeNotEqual" :
                    responseCodeNotEqual(assertExp);
                    break;
                case "ResponseContain":
                    responseContain(assertExp);
                    break;
                default:
                    responseCodeEqual(assertExp);
            }
        }
        return map;
    }

    public void responseCodeEqual(AssertExp exp){
        AssertResult assertResult = new AssertResult(exp, true);

        if (result.getResponseCode().equals( exp.getParams())) {
            assertResult.setSuccess(true);
        }
        else {
            assertResult.setSuccess(false);
            this.success = false;
        }
        assertResults.add(assertResult);
        resultMap.put("detail",assertResults);
        resultMap.put("success",success);
        map.put("assertResult",resultMap);

    }

    public void responseCodeNotEqual(AssertExp exp){
        AssertResult assertResult = new AssertResult(exp, false);
        if (!result.getResponseCode().equals(exp.getParams()))
            assertResult.setSuccess(true);
        else {
            assertResult.setSuccess(false);
            this.success = false;
        }
        assertResults.add(assertResult);
        resultMap.put("detail",assertResults);
        resultMap.put("success",success);
        map.put("assertResult",resultMap);
    }

    public void responseContain(AssertExp exp){
        AssertResult assertResult = new AssertResult(exp, false);
        if (result.getResponseBody().contains(exp.getParams())){
            assertResult.setSuccess(true);
        }else {
            assertResult.setSuccess(false);
            this.success = false;
        }
        assertResults.add(assertResult);
        resultMap.put("detail",assertResults);
        resultMap.put("success",success);
        map.put("assertResult",resultMap);
    }
}
