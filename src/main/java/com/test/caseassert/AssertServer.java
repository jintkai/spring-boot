package com.test.caseassert;

import com.test.suit.SuitResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssertServer {


    private Map<String,Object> map = new HashMap<>();

    private List<AssertExp> assertExpList;
    private SuitResult result;

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
            String params = assertExp.getParams();
            switch (methodName){
                case "ResponseCodeEqual" :
                    responseCodeEqual(assertExp);
                    break;
                case "ResponseCodeNotEqual" :
                    responseCodeNotEqual(assertExp);
                    break;
                default:
                    responseCodeEqual(assertExp);
            }
        }
        return map;
    }

    public void responseCodeEqual(AssertExp exp){
        if (result.getStatus().equals( exp.getParams()))
            map.put("success",true);

        else
            map.put("success",false);
    }

    public void responseCodeNotEqual(AssertExp exp){
        if (!result.getStatus().equals(exp.getParams()))
            map.put("success",true);
        else
            map.put("success",false);
    }
}
