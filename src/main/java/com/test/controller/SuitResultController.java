package com.test.controller;

import com.test.suit.domain.SuitResult;
import com.test.suit.service.SuitResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by ying.zhang on 17/10/10.
 */
@Controller
@RequestMapping("/suitResult")
public class SuitResultController {

    @Autowired
    private SuitResultService suitResultService;

    @RequestMapping("/getById")
    @ResponseBody
    public SuitResult getSuitResultById(int id){return suitResultService.getSuitResultById(id);}

    @RequestMapping("/getBySuitIdAndbuildId")
    @ResponseBody
    public List<SuitResult> getBySuitIdAndbuildId(int suitId, int buildId){
        return suitResultService.getBySuitIdAndbuildId(suitId,buildId);
    }

    @RequestMapping("/save")
    @ResponseBody
    public int saveSuitResult(SuitResult suitResult){
        return suitResultService.saveSuitResult(suitResult);
    }

    @RequestMapping("/update")
    @ResponseBody
    public int updateSuitResult(SuitResult suitResult){
        return suitResultService.updateSuitResult(suitResult);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public int daleteSuitResult(int id){
        return suitResultService.deleteSuitResult(id);
    }
}

