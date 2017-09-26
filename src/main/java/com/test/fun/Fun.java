package com.test.fun;

import com.test.suit.domain.CaseSuit;
import com.test.suit.service.CaseSuitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 自定义函数类，用户自定义函数放在此类中，同时也负责解析用户输入的function表达式*
 * FUN_AA(a,b);
 * a,b参数不能包含字符,(,),FUN_
 * 方法的返还结果中也不能包含,(,),FUN_
 */
public class Fun {


    public String resolveFun(String funExp){

        String str = "FUN_AA(a,FUN_B(),FUN_CD(a,FUN_BD(b,d)))";
        while (true) {
            if (str.contains("FUN_")) {
                int end = str.indexOf(")");
                String tmp = str.substring(0,end+1);
                int begin = tmp.lastIndexOf("FUN_");
                String fun = tmp.substring(begin);
                String funReturn = "";

                if (fun.contains("FUN_B("))
                    funReturn = FUN_B();
                else if (fun.contains("FUN_BD("))
                    funReturn = FUN_BD(fun);
                else if(fun.contains("FUN_CD("))
                    funReturn = FUN_CD(fun);
                else if(fun.contains("FUN_AA("))
                    funReturn = FUN_AA(fun);
                fun = fun.replace("(","\\(").replace(")","\\)");
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


}
