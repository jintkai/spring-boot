package com.test.fun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.testng.annotations.Test;

@Component
public class FunService {

    @Autowired
    Fun fun ;

    public String resolveFun(String source,Object object){
        String result = source;
        int begin = source.indexOf("{{");
        int end = source.indexOf("}}");
        if (-1 == begin || -1 ==end){
            //不需要解析，直接返回原始数据
            //return result;
        }else{
            while ( -1 !=begin && -1 !=end){
                String tmp = result.substring(begin+2,end);
                String reTmp = result.substring(begin,end+2);
                if (null != object)
                    result = result.replace(reTmp,fun.resolveFun(tmp,object));
                else
                    result = result.replace(reTmp,fun.resolveFun(tmp));
                begin = result.indexOf("{{");
                end = result.indexOf("}}");

            }
        }
        return result;
    }



}

