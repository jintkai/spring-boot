package com.test.autocase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jon.httpclient.HttpClientService;
import com.test.caseassert.service.AssertServer;
import com.test.suit.domain.CaseSuit;
import com.test.suit.domain.SuitResult;
import com.test.suit.service.CaseSuitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CaseControllerService {

    private static final Logger LOG = LoggerFactory.getLogger(CaseControllerService.class);

    @Autowired
    CaseSuitService caseSuitService;
    
    @Autowired
    HttpClientService httpClientService;

    @Autowired
    AssertServer assertServer;


    public void runCase(String suitKey,boolean isNew){
        List<CaseSuit> cases = caseSuitService.getCaseBySuit(suitKey,isNew);

        if ( cases.isEmpty()){
            LOG.info("case集合空，无需执行用例。");
            return;
        }else {
            for (CaseSuit casesuit :cases) {
                LOG.debug("CaseSuit:"+casesuit.toString());
                Map<String,String> map = httpClientService.sentRequest(casesuit.getRequestType(),casesuit.getRequestUrl(),casesuit.getRequestHeader(),
                        casesuit.getRequestParamets());

                Map<String,Object> requestMap = new HashMap<String,Object>();
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
                result = new SuitResult(casesuit.getId(),casesuit.getBuildId(),casesuit.getSuitKey(),map.get("ResponseHeaders"),map.get("ResponseBody"),
                        map.get("ResponseCode"),Float.valueOf(map.get("ResponseTime")),"",0);
                    String exp = casesuit.getAssertExp();
                int resultStatus = 1;
                String r ="";
                //断言转list<String>
                if (null != exp && !exp.isEmpty()){
                    try {
                        List<String> assertExpList = new ObjectMapper().readValue(exp,new TypeReference<List<String>>() { });
                        assertServer.setResult(result);
                        Object object = assertServer.assertResult(assertExpList,result);
                         r = mapper.writeValueAsString(object);
                        Map<String,Object> resultMap = (Map<String, Object>) assertServer.assertResult(assertExpList,result).get("assertResult");

                        if((boolean) resultMap.get("success")){
                            resultStatus = 1;
                        }
                        else
                            resultStatus = 2;


                    } catch (IOException e) {
                        resultStatus = 2;
                        r = "断言表达式格式错误！";
                        LOG.error("断言转list<String>失败："+exp);
                        LOG.error(e.toString());
                    }
                    result.setAssertLog(r);
                    result.setStatus(resultStatus);
                }else{
                    result.setAssertLog("未设置断言表达式!");
                    result.setStatus(resultStatus);

                }

                caseSuitService.saveResult(result,isNew);
            }
            //统计
            caseSuitService.saveReport(suitKey,String.valueOf(cases.get(0).getBuildId()));
        }
    }
}


