package com.test.suit.controller;

import com.test.suit.domain.SuitReport;
import com.test.suit.domain.SuitResult;
import com.test.suit.service.CaseSuitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/suit")
public class SuitController {

    @Autowired
    CaseSuitService caseSuitService;

    @RequestMapping(value = "/getReport",method = RequestMethod.POST)
    public Map<String,Object> getBuildResult(@RequestParam String suitKey,@RequestParam int buildID){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        List<SuitReport>  list = caseSuitService.getBuildResult(suitKey,buildID);
        resultMap.put("detail",list);
        resultMap.put("size",list.size());
        int success = 0;
        int fail = 0;
        int unExecution = 0;
        int total = list.size();
        for (SuitReport suitResult:list){
            switch (Integer.valueOf(suitResult.getStatus())){
                case 1:
                    success+=1;
                    break;
                case 2:
                    fail+=1;
                    break;
                default:
                    unExecution+=1;
                    break;
            }
        }
        resultMap.put("summary",success+"/"+fail+"/"+unExecution+"/"+total);
        return resultMap;
    }

    public Map<String,Object> executeBuild(@RequestParam String suitKey,@RequestParam boolean isNew){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        return resultMap;
    }
}
