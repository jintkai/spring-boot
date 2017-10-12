package com.test.report;

import com.test.suit.domain.SuitReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/suit/report")
public class SuitBuildRecordController {

    @Autowired
    SuitBuildRecordService suitBuildRecordService;

    @RequestMapping(value = "/buildHis",method = RequestMethod.POST)
    public Map<String,Object> getBuildResult(@RequestParam String suitKey){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        List<SuitBuildRecord> list = suitBuildRecordService.getSuitBuildRecordList(suitKey);
        resultMap.put("detail",list);
        return resultMap;
    }
}
