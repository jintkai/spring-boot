package com.jon;

import com.test.autocase.CaseControllerService;
import com.test.fun.Fun;
import com.test.suit.service.CaseSuitService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ContextConfiguration(classes = com.jon.Config.class)

public class CaseSuitTest  extends AbstractTestNGSpringContextTests {

    @Autowired
    CaseControllerService caseControllerService;

    @Autowired
    CaseSuitService caseSuitService;
    @Test
    public void test(){
        caseControllerService.runCase("service-platform",true);
    }

    @Test
    public void getLastBuildID(){
        System.out.println(caseSuitService.getLastBuild("service-platform"));
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


    @Test
    public void test1(){

        //System.out.println(fun.toString());
        //String exp = "$FUN_SUBSTRING_($FUN_SUBSTRING_( hello,world _, _,d _)_,e_,r_)";
        //String json = "{\"message\":\"插入项目信息失败，项目key已经存在！\",\"status\":0}";


        String exp = "$FUN_JSON_($FUN_JSON_(  {\"message\":{\"a\":\"插入项目信息失败，项目key已经存在！\",\"b\":\"123\"},\"status\":0} _,message _)_,a_)";


    }

    public String fun4(String b){
        return b;
    }
    public String fun3(String a,String b){
        return a+b;
    }
    public String fun2(){
        return new Date().toString();
    }
    public String fun1(String a,String b,String c){
        return a+b+c;
    }
}
