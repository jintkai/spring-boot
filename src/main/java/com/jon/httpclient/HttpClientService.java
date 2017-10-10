package com.jon.httpclient;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class HttpClientService {

    public static final Logger LOG = LoggerFactory.getLogger(HttpClientService.class);

    public Map<String, String> sentRequest(int requestType, String requestUrl, String requestHeader, String requestParameters) {

        Map<String, String> map = new HashMap<String, String>();
        Class<?> cls = null;
        String className = "";
        switch (requestType) {
            case 0:
                className = "org.apache.commons.httpclient.methods.GetMethod";
                break;
            case 1:
                className = "org.apache.commons.httpclient.methods.PostMethod";
                break;
            case 2:
                className = "org.apache.commons.httpclient.methods.PostMethod";
                break;
            case 3:
                className = "org.apache.commons.httpclient.methods.DeleteMethod";
                break;
            default:
                className = "org.apache.commons.httpclient.methods.GetMethod";
        }
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            LOG.error(e.toString());
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
        JSONObject jsonObject;

        //请求头转json
        if (!requestHeader.isEmpty()) {
            LOG.info("requestHeader:"+requestHeader);
            if(isJson(requestHeader)) {
                try {
                    jsonObject = new JSONObject(requestHeader);
                    Iterator iterator = jsonObject.keys();
                    while (iterator.hasNext()) {
                        String key = (String) iterator.next();
                        method.setRequestHeader(key, jsonObject.getString(key));
                    }
                } catch (Exception e) {
                    LOG.error("requestHeader转json失败。" + e.toString());
                }
            }else{
                LOG.error("requestHeader不是json格式，无法转换。" );
            }
        }


        try {
            method.setURI(new URI(requestUrl));

            if (null != requestParameters && !requestParameters.isEmpty()) {
                Header header = method.getRequestHeader("Content-Type");
                //非json
                if (null == header || !header.getValue().contains("application/json")) {
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
                    //form表单
                    if (1 == requestType) {
                        Part[] parts = new Part[lists.size()];
                        for (int i =0;i<lists.size();i++){
                            parts[i] = new StringPart(lists.get(i).getName(),lists.get(i).getValue());
                        }
                        method = (PostMethod) method;
                        ((PostMethod) method).setRequestEntity(new MultipartRequestEntity(parts, method.getParams()));
                    } else {
                        method.setQueryString(pairs);
                    }
                    map.put("RequestParam", Arrays.toString(pairs));
                }
                else {
                    //json格式
                    RequestEntity entity = new StringRequestEntity(requestParameters);
                    ((PostMethod) method).setRequestEntity(entity);

                    map.put("RequestParam", requestParameters);
                }
            }

            map.put("URI", method.getURI().toString());
        } catch (URIException e) {
            e.printStackTrace();
        }

        HttpClient httpClient = new HttpClient();





        try {
            Date begin = new Date();

            httpClient.executeMethod(method);
            Date end = new Date();
            long responseTime = end.getTime() - begin.getTime();
            map.put("ResponseTime", String.valueOf(responseTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            String response = method.getResponseBodyAsString();
            map.put("ResponseCode", String.valueOf(method.getStatusCode()));
            map.put("ResponseBody", response);
            map.put("RequestHeaders", Arrays.toString(method.getRequestHeaders()));
            map.put("ResponseHeaders", Arrays.toString(method.getResponseHeaders()));
            return map;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isJson(String str) {
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
