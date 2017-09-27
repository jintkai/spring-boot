package com.test.autocase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jon.httpclient.HttpClientService;
import com.test.caseassert.domain.AssertExp;
import com.test.caseassert.service.AssertServer;
import com.test.suit.domain.CaseSuit;
import com.test.suit.service.CaseSuitService;
import com.test.suit.domain.SuitResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CaseControllerService {

    @Autowired
    CaseSuitService caseSuitService;
    
    @Autowired
    HttpClientService httpClientService;


    public void runCase(){

        //List<CaseSuit> cases = caseSuitService.getCaseBySuit("ocean_web");
        List<CaseSuit> cases = caseSuitService.getCaseBySuitAndBuildID("ocean_web",11);

        if ( cases.isEmpty()){
            return;
        }else {
            for (CaseSuit casesuit :cases) {
                Map<String,String> map = httpClientService.sentRequest(casesuit.getRequestType(),casesuit.getRequestUrl(),casesuit.getRequestHeader(),
                        casesuit.getRequestParamets());

                Map<String,Object> requestMap = new HashMap<>();
                requestMap.put("RequestHeaders",map.get("RequestHeaders"));
                requestMap.put("RequestParam",map.get("RequestParam"));
                requestMap.put("URI",map.get("URI"));
                ObjectMapper mapper = new ObjectMapper();

                String requestInfo = null;
                try {
                    requestInfo = mapper.writeValueAsString(requestMap);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                SuitResult result;
                result = new SuitResult(casesuit.getId(),casesuit.getBuildId(),requestInfo,map.get("ResponseHeaders"),map.get("ResponseBody"),
                        map.get("ResponseCode"),Integer.valueOf(map.get("ResponseTime")));

                String exp = casesuit.getAssertExp();
                int resultStatus = 0;
                if (null != exp && !exp.isEmpty()){
                    try {
                        List<AssertExp> assertExpList = new ObjectMapper().readValue(exp,new TypeReference<List<AssertExp>>() { });
                        AssertServer assertServer = new AssertServer(assertExpList,result);
                        String r = mapper.writeValueAsString(assertServer.assertResult().get("assertResult"));
                        Map<String,Object> resultMap = (Map<String, Object>) assertServer.assertResult().get("assertResult");

                        if((boolean) resultMap.get("success")){
                            resultStatus = 1;
                        }
                        else
                            resultStatus = 2;
                        result.setAssertLog(r);
                        result.setStatus(resultStatus);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                caseSuitService.saveCaseResultLog(result);

            }
        }
    }
}


