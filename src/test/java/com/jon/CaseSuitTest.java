package com.jon;

import com.test.autocase.CaseControllerService;
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
    //@Test
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


    public void test(String ... arg){



        String url = "http://172.12.1.123/test.txt";
        String regex = "(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);

        //System.out.println("是否匹配:" + matcher.matches());
        while (matcher.find()) {
            String result = matcher.group(1);
            System.out.println("result = "+result);
        }
        String str = "FUN_AA(a,FUN_B(),FUN_CD1(a,FUN_A78D(b)))";

        String regex1 = "FUN_B.+\\(";
        pattern =Pattern.compile(regex1);
        matcher = pattern.matcher(str);
        while (matcher.find()) {
            String result = matcher.group(1);
            System.out.println("matcher = "+result);
        }

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
