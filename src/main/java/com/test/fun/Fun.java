package com.test.fun;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.suit.dao.SuitResultDaoImp;
import com.test.suit.domain.CaseSuit;
import com.test.suit.domain.SuitResult;
import com.test.suit.service.CaseSuitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.test.suit.service.CaseSuitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class Fun {

    @Autowired
    CaseSuitService caseSuitService;

    @Autowired
    SuitResultDaoImp suitResultDaoImp;

    @Deprecated
    public String resolveFun(String funExp){
        String str = funExp;
        while (true) {
            if (str.contains("Fun")) {
                int end = str.indexOf("_)");
                String tmp = str.substring(0, end + 2);
                int begin = tmp.lastIndexOf("Fun");
                String fun = tmp.substring(begin);
                String funReturn = "";
                if (fun.contains("FunSuit_("))
                    funReturn = funGetBodyBySuitID(fun);
                if (fun.contains("FunSUBSTRING_("))
                    funReturn = funGetSubstring(fun);
                if (fun.contains("FunJson_("))
                    funReturn = funGetJsonData(fun);
                if (fun.contains("FunBodyJson_("))
                    funReturn = getBodyJson(fun,null);
                if (fun.contains("FunRANDOM_("))
                    funReturn = funRandom(fun);
                if (fun.contains("FunEqual_("))
                    funReturn = funEqual(fun);

                // * . ? + $ ^ [ ] ( ) { } | \ /
                fun = fun.replace("(", "\\(").replace(")", "\\)").replace("$", "\\$");
                fun = fun.replace("{", "\\{").replace("}", "\\}");
                fun = fun.replace(".", "\\.").replace("/", "\\/");
                fun = fun.replace("*", "\\*").replace("?", "\\?");
                fun = fun.replace("+", "\\+").replace("^", "\\^").replace("[", "\\[").replace("]", "\\]");
                fun = fun.replace("|", "\\|");
                Pattern p = Pattern.compile(fun);
                Matcher m = p.matcher(str);
                str = m.replaceFirst(funReturn);
            } else
                break;
        }
        return str;

    }

    public String resolveFun(String funExp,Object object){
        String str = funExp;
        while (true) {
            if (str.contains("Fun")) {
                int end = str.indexOf("_)");
                String tmp = str.substring(0, end + 2);
                int begin = tmp.lastIndexOf("Fun");
                String fun = tmp.substring(begin);
                String funReturn = "";
                if (fun.contains("FunResponseCodeEqual_("))
                    funReturn = funResponseCodeEqual(fun,object);
                if (fun.contains("FunResponseJson_("))
                    funReturn = getBodyJson(fun,object);
                if (fun.contains("FunSuit_("))
                    funReturn = funGetBodyBySuitID(fun,object);
                else if (fun.contains("FunSUBSTRING_("))
                    funReturn = funGetSubstring(fun);
                if (fun.contains("FunJson_("))
                    funReturn = funGetJsonData(fun);
                if (fun.contains("FunBodyJson_("))
                    funReturn = getBodyJson(fun,object);
                if (fun.contains("FunRANDOM_("))
                    funReturn = funRandom(fun);
                if (fun.contains("FunEqual_("))
                    funReturn = funEqual(fun);
                if (fun.contains("FunResponseCodeNotEqual_("))
                    funReturn = funResponseCodeNoteEqual(fun,object);
                // * . ? + $ ^ [ ] ( ) { } | \ /
                fun = fun.replace("(", "\\(").replace(")", "\\)").replace("$", "\\$");
                fun = fun.replace("{", "\\{").replace("}", "\\}");
                fun = fun.replace(".", "\\.").replace("/", "\\/");
                fun = fun.replace("*", "\\*").replace("?", "\\?");
                fun = fun.replace("+", "\\+").replace("^", "\\^").replace("[", "\\[").replace("]", "\\]");
                fun = fun.replace("|", "\\|");
                Pattern p = Pattern.compile(fun);
                Matcher m = p.matcher(str);
                str = m.replaceFirst(funReturn);
            } else
                break;
        }
        return str;
    }



    public String funGetSubstring(String fun) {
        String source = fun.substring(fun.indexOf("_(") + 2, fun.indexOf("_,"));
        int firstIndex = fun.indexOf("_,");
        int secendIndex = fun.indexOf("_,", firstIndex + 1);
        String begin = fun.substring(firstIndex + 2, secendIndex);
        String end = fun.substring(secendIndex + 2, fun.indexOf("_)"));
        String result = source.substring(source.indexOf(begin) + begin.length(), source.indexOf(end));
        return result;
    }

    public String funGetJsonValue(String fun) {
        String json = fun.substring(fun.indexOf("_(") + 2, fun.indexOf("_,"));
        String key = fun.substring(fun.indexOf("_,") + 2, fun.indexOf("_)")).trim();

        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> map = new HashMap<String,Object>();
        String value = "";
        try {
            //mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
            //mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
            map = mapper.readValue(json, new TypeReference<Map<String, Object>>() {
            });
            JsonNode node = mapper.readTree(json);
            String s = node.get(key).toString();
            //value = String.valueOf(map.get(key));
            value = s;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return value;
    }

    public String funRandom(String fun) {
        int r = Integer.parseInt(fun.substring(fun.indexOf("_(") + 2, fun.indexOf("_)")));
        int i = ((int) (Math.random() * 10)) % r;
        return String.valueOf(i);
    }



    //获取json字符串的param部分（json，param.param1.param2）
    public String funGetJsonData(String fun) {
        String json = fun.substring(fun.indexOf("_(") + 2, fun.indexOf("_,"));
        String param = fun.substring(fun.indexOf("_,") + 2, fun.indexOf("_)")).trim();
        if (!param.contains(".")) {
            return getJsonValue(json, param);
        } else {
            String[] keys = param.split(".");
            for (int i = 0; i < keys.length; i++) {
                json = getJsonValue(json, keys[i]);
            }
            return json;
        }
    }


    public String funGetJsonData(String key,String json){
        if (!key.contains(".")) {
            return getJsonValue(json, key);
        } else {
            String[] keys = key.split("\\.");
            for (int i = 0; i < keys.length; i++) {
                json = getJsonValue(json, keys[i]);
            }
            return json;
        }
    }

    public String getJsonValue(String json, String key) {
        //String json = fun.substring(fun.indexOf("_(")+2,fun.indexOf("_,"));
        //String key = fun.substring(fun.indexOf("_,")+2,fun.indexOf("_)")).trim();


        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> map = new HashMap<String,Object>();
        String value = "";
        try {
            map = mapper.readValue(json, new TypeReference<Map<String, Object>>() {
            });
            JsonNode node = mapper.readTree(json);
            node = node.get(key);
            //获取text的方法有问题
            if(node.isTextual()){
                value = node.toString().substring(1, node.toString().length() - 1);
            }else{
                value = node.asText();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return value;
    }
    //获取json格式的body
    public String getBodyJson(String fun,Object object){
        String key = fun.substring(fun.indexOf("_(") + 2, fun.indexOf("_)"));
        String body = ((SuitResult)object).getResponseBody();

        return funGetJsonData(key,body);
    }

    //获取case的body
    public String funGetBodyBySuitID(String fun,Object object) {
        int suitId = Integer.parseInt(fun.substring(fun.indexOf("_(") + 2, fun.indexOf("_)")));
        List<SuitResult> suitResults = caseSuitService.getSuitCaseResultByID(suitId, ((SuitResult)object).getBuildId());
        return suitResults.get(0).getResponseBody();
    }
    public String funGetBodyBySuitID(String fun) {
        int suitId = Integer.parseInt(fun.substring(fun.indexOf("_(") + 2, fun.indexOf("_)")));
        int buildId = suitResultDaoImp.getLastBuildIdBySuitId(String.valueOf(suitId));
        List<SuitResult> suitResults = caseSuitService.getSuitCaseResultByID(suitId,buildId);
        return suitResults.get(0).getResponseBody();
    }

    public String funResponseCodeEqual(String fun,Object object){
        String body = ((SuitResult)object).getResponseCode();
        String code = fun.substring(fun.indexOf("_(") + 2, fun.indexOf("_)"));
        return String.valueOf(code.equals(body));
    }

    public String funResponseCodeNoteEqual(String fun,Object object){
        String body = ((SuitResult)object).getResponseCode();
        String code = fun.substring(fun.indexOf("_(") + 2, fun.indexOf("_)"));
        return String.valueOf(!code.equals(body));
    }


    public String funEqual(String fun){
        String expect = fun.substring(fun.indexOf("_(") + 2, fun.indexOf("_,")).trim();
        String actual = fun.substring(fun.indexOf("_,") + 2, fun.indexOf("_)")).trim();

        return String.valueOf(expect.equals(actual));
    }

}
