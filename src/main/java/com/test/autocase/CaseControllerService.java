package com.test.autocase;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jon.httpclient.HttpClientService;
import com.test.caseassert.AssertExp;
import com.test.caseassert.AssertServer;
import com.test.suit.CaseSuit;
import com.test.suit.CaseSuitService;
import com.test.suit.SuitResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class CaseControllerService {

    @Autowired
    CaseSuitService caseSuitService;
    
    @Autowired
    HttpClientService httpClientService;


    public void runCase(){

        List<CaseSuit> cases = caseSuitService.getCaseBySuit("ocean_web");
        if ( cases.isEmpty()){
            return;
        }else {
            for (CaseSuit casesuit :cases) {
                Map<String,String> map = httpClientService.sentRequest(casesuit.getRequestType(),casesuit.getRequestUrl(),casesuit.getRequestHeader(),
                        casesuit.getRequestParamets());

                String detail = "QueryString:"+map.get("QueryString")+",RequestHeaders:"+map.get("RequestHeaders");
                SuitResult result = new SuitResult(casesuit.getId(),detail,map.get("StatusCode"),
                        map.get("ResponseBody"),map.get("ResponseTime"),"",0);

                String exp = casesuit.getAssertExp();

                if (null != exp && !exp.isEmpty()){
                    try {
                        List<AssertExp> assertExpList = new ObjectMapper().readValue(exp,new TypeReference<List<AssertExp>>() { });
                        AssertServer assertServer = new AssertServer(assertExpList,result);
                        String r = String.valueOf(assertServer.assertResult().get("success"));
                        result.setAssertLog(r);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                caseSuitService.saveCaseResultLog(result);

            }
        }
    }
}


