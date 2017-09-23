package com.jon;

import com.test.autocase.CaseControllerService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

@ContextConfiguration(classes = com.jon.Config.class)

public class CaseSuitTest  extends AbstractTestNGSpringContextTests {

    @Autowired
    CaseControllerService caseControllerService;
    @Test
    public void test(){
        caseControllerService.runCase();
    }
    @Test
    public void test2(){
        String str = "key=998,name=test,desc=description";
        String[] strs = str.split(",");
        Map<String, String> m = new HashMap<String,String>();
        for(String s:strs){
            String[] ms = s.split("=");
            m.put(ms[0], ms[1]);
        }
        return;
    }

    @Test
    public void test3(){
        String str = "{key=998,name=test,desc=description}";
        boolean isJson = false;
        try {
            JSONObject jsonObject = new JSONObject(str);
            isJson = true;
        } catch (JSONException e) {
            isJson = false;
        }
        return ;
    }
}
