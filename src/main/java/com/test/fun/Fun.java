package com.test.fun;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.suit.domain.CaseSuit;
import com.test.suit.service.CaseSuitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 自定义函数类，用户自定义函数放在此类中，同时也负责解析用户输入的function表达式*
 * FUN_AA(a,b);
 * a,b参数不能包含字符,(,),FUN_
 * 方法的返还结果中也不能包含,(,),FUN_
 */
@Service
public class Fun {


    public String resolveFun(String funExp){
        String str = funExp;
        while (true) {
            if (str.contains("$FUN_")) {
                int end = str.indexOf("_)");
                String tmp = str.substring(0,end+2);
                int begin = tmp.lastIndexOf("$FUN_");
                String fun = tmp.substring(begin);
                String funReturn = "";

                if (fun.contains("$FUN_B_("))
                    funReturn = FUN_B();
                else if (fun.contains("FUN_BD_("))
                    funReturn = FUN_BD(fun);
                else if(fun.contains("FUN_CD_("))
                    funReturn = FUN_CD(fun);
                else if(fun.contains("FUN_AA_("))
                    funReturn = FUN_AA(fun);
                else if (fun.contains("$FUN_SUBSTRING_("))
                    funReturn = funGetSubstring(fun);
                if (fun.contains("$FUN_JSON_"))
                    funReturn = funGetJsonValue(fun);

                // * . ? + $ ^ [ ] ( ) { } | \ /
                fun = fun.replace("(","\\(").replace(")","\\)").replace("$","\\$");
                fun = fun.replace("{","\\{").replace("}","\\}");
                fun = fun.replace(".","\\.").replace("/","\\/");
                fun = fun.replace("*","\\*").replace("?","\\?");
                fun = fun.replace("+","\\+").replace("^","\\^").replace("[","\\[").replace("]","\\]");
                fun = fun.replace("|","\\|");
                Pattern p = Pattern.compile(fun);
                Matcher m = p.matcher(str);
                str = m.replaceFirst(funReturn);
            }
            else
                break;
        }
        return str;

    }


    public String FUN_B(){
        return "fun!"+"H"+"!";
    }
    public String FUN_BD(String fun){
        String a = fun.substring(fun.indexOf("(")+1,fun.indexOf(","));
        String b = fun.substring(fun.indexOf(",")+1,fun.indexOf(")"));
        return "fundb!"+a+b+"!";
    }
    public String FUN_CD(String fun){
        return new Date().toString();
    }
    public String FUN_AA(String fun){
        String a = fun.substring(fun.indexOf("(")+1,fun.indexOf(","));
        String b = fun.substring(fun.indexOf(",")+1,fun.indexOf(")"));
        return "funaa!"+a+b+"!";
    }

    public String FUN_DB(String sql){
        return "";
    }

    public String funGetSubstring(String fun){
        String source = fun.substring(fun.indexOf("_(")+2,fun.indexOf("_,"));
        int firstIndex = fun.indexOf("_,");
        int secendIndex = fun.indexOf("_,",firstIndex+1);
        String begin = fun.substring(firstIndex+2,secendIndex);
        String end = fun.substring(secendIndex+2,fun.indexOf("_)"));
        String result = source.substring(source.indexOf(begin)+begin.length(),source.indexOf(end));

        return result;
    }

    public String funGetJsonValue(String fun){
        String json = fun.substring(fun.indexOf("_(")+2,fun.indexOf("_,"));
        String key = fun.substring(fun.indexOf("_,")+2,fun.indexOf("_)")).trim();


        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> map = new HashMap<>();
        String value = "";
        try {
            //mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
            //mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
            map = mapper.readValue(json, new TypeReference<Map<String, Object>>(){});
            JsonNode node = mapper.readTree(json);
            String s = node.get(key).toString();
            //value = String.valueOf(map.get(key));
            value = s;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return value;
    }

}
