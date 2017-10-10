package com.test.caseassert.service;

import com.test.caseassert.domain.AssertExp;
import com.test.caseassert.domain.AssertResult;
import com.test.fun.FunService;
import com.test.suit.domain.SuitResult;
import org.springframework.beans.factory.annotation.Autowired;
import com.test.suit.domain.SuitResult;
import org.springframework.stereotype.Component;
import sun.reflect.generics.scope.Scope;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AssertServer {

    @Autowired
    FunService funService;


    private Map<String,Object> map = new HashMap<String,Object>();

    private List<AssertExp> assertExpList;
    private List<AssertResult> assertResults;
    private boolean success = true;
    private SuitResult result;

    private Map<String,Object> resultMap = new HashMap<String,Object>();

    public AssertServer(List<AssertExp> list, SuitResult result) {
        this.assertExpList = list;
        this.result = result;
    }

    public void init(){
        map = new HashMap<String,Object>();
        assertExpList = new ArrayList<AssertExp>();
        assertResults = new ArrayList<AssertResult>();
        success = true;
        resultMap = new HashMap<String,Object>();
    }

    public AssertServer() {
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public List<AssertExp> getAssertExpList() {
        return assertExpList;
    }

    public void setAssertExpList(List<AssertExp> assertExpList) {
        this.assertExpList = assertExpList;
    }

    public List<AssertResult> getAssertResults() {
        return assertResults;
    }

    public void setAssertResults(List<AssertResult> assertResults) {
        this.assertResults = assertResults;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public SuitResult getResult() {
        return result;
    }

    public void setResult(SuitResult result) {
        this.result = result;
    }

    public Map<String, Object> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }
    public Map<String,Object> assertResult(List<String> list,SuitResult suitResult){
        init();
        this.success = true;

        for(int i = 0;i<list.size();i++){
            String result = list.get(i);
            if (result.equals("false")){
                this.success = false;
            }
        }

        resultMap.put("detail",list);
        resultMap.put("success",success);
        map.put("assertResult",resultMap);
        return map;
    }
}
