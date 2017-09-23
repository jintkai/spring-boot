package com.jon.httpclient;

import javafx.geometry.Pos;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.*;

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
            if ( null != requestParameters && !requestParameters.isEmpty()) {
                if(!isJson(requestParameters)) {
                    List<NameValuePair> lists = new ArrayList<NameValuePair>();
                    String str = requestParameters;
                    String[] strs = str.split(",");
                    Map<String, String> m = new HashMap<String, String>();
                    for (int i = 0; i < strs.length; i++) {
                        String s = strs[i];
                        String[] ms = s.split("=");
                        m.put(ms[0], ms[1]);
                        lists.add(new NameValuePair(ms[0], ms[1]));
                    }
                    NameValuePair[] pairs = new NameValuePair[lists.size()];
                    for (int i = 0; i < lists.size(); i++) {
                        pairs[i] = lists.get(i);
                    }
                    method.setQueryString(pairs);
                    map.put("QueryString", Arrays.toString(pairs));
                }else{
                    RequestEntity entity = new StringRequestEntity(requestParameters) ;
                    ((PostMethod)method).setRequestEntity(entity);

                    map.put("QueryString",requestParameters);
                    method.setRequestHeader(new Header("Content-Type","application/json"));
                }

            }
        } catch (URIException e) {
            e.printStackTrace();
        }

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
            map.put("RequestHeaders",Arrays.toString(method.getRequestHeaders()));
            return map;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isJson(String str){
        boolean isJson = false;
        try {
            JSONObject jsonObject = new JSONObject(str);
            isJson = true;
        } catch (JSONException e) {
            isJson = false;
        }
        return isJson;
    }

}
