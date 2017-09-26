package com.jon;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Fun {

    public static void main(String ... arg){
        String url = "http://172.12.1.123/test.txt";
        String regex = "(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);

        //System.out.println("是否匹配:" + matcher.matches());
        while (matcher.find()) {
            String result = matcher.group(0);
            System.out.println("result = "+result);
        }
        String str = "FUN_AA(a,FUN_B(),FUN_CD1(a,FUN_B78D(b)))";

        String regex1 = "FUN_B.+\\(.*\\)$";
        pattern =Pattern.compile(regex1);
        matcher = pattern.matcher(str);
        System.out.println(str);
        while (matcher.find()) {
            String result = matcher.group(0);

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
