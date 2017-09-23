package com.jon.httpclient;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class HttpClientService {

    public Map<String,String> sentRequest(int requestType, String requestUrl, String requestHeader, String requestParameters) {

        Map<String,String> map = new HashMap<String, String>();
        Class<?> cls = null;
        String className = "";
        switch (requestType){
            case 0:
                className = "org.apache.commons.httpclient.methods.GetMethod";
                break;
            case 1:
                className = "org.apache.commons.httpclient.methods.PostMethod";
                break;
            default:
                className = "org.apache.commons.httpclient.methods.GetMethod";
        }
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Object object = null;
        try {
            object = cls.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        HttpMethod method = (HttpMethod) object;
        try {
            method.setURI(new URI(requestUrl));
            method.setQueryString(requestParameters);
            method.getResponseFooters();
            //method.setParams();
        } catch (URIException e) {
            e.printStackTrace();
        }
        //GetMethod method = new GetMethod(requestUrl);

        HttpClient httpClient = new HttpClient();
        try {
            Date begin = new Date();
            httpClient.executeMethod(method);
            Date end = new Date();
            long responseTime = end.getTime() - begin.getTime();
            map.put("ResponseTime",String.valueOf(responseTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            String response = method.getResponseBodyAsString();
            map.put("StatusCode",String.valueOf(method.getStatusCode()));
            map.put("ResponseBody",response);
            method.getRequestHeaders().toString();
            return map;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
