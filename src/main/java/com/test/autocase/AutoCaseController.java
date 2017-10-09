package com.test.autocase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/autoCase")
public class AutoCaseController {

    @Autowired
    CaseControllerService caseControllerService;

    @RequestMapping (value = "/run",method = RequestMethod.POST)
    public Map<String,Object> runCase(@RequestParam String suitKey,@RequestParam boolean isNew){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        caseControllerService.runCase(suitKey,isNew);
        return resultMap;

    }
}
