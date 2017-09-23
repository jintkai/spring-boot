package com.test.autocase;

import com.jon.httpclient.HttpClientService;
import com.test.suit.CaseSuit;
import com.test.suit.CaseSuitService;
import com.test.suit.SuitResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
                SuitResult result = new SuitResult(casesuit.getId(),casesuit.getRequestParamets(),map.get("StatusCode"),
                        map.get("ResponseBody"),map.get("ResponseTime"));
                caseSuitService.saveCASEResultLog(result);
            }
        }
    }
}


