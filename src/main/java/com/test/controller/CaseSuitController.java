package com.test.controller;

import com.test.suit.domain.CaseSuit;
import com.test.suit.service.CaseSuitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by ying.zhang on 17/10/2.
 */
@Controller
@RequestMapping("/caseSuit")
public class CaseSuitController {

    @Autowired
    private CaseSuitService caseSuitService;

    @RequestMapping("/getBySuitId")
    @ResponseBody
    public List<CaseSuit> getCaseBySuit(String suitId){
        return caseSuitService.getCaseSuitBySuit(suitId);
    }

    @RequestMapping("/getBySuitAndBuildID")
    @ResponseBody
    public List<CaseSuit> getCaseBySuitAndBuildID(String suitId,int buildId){
        return caseSuitService.getCaseBySuitAndBuildID(suitId,buildId);
    }

    @RequestMapping("/getBySuitKey")
    @ResponseBody
    public List<CaseSuit> getCaseSuitBySuit(String suitKey){
        return caseSuitService.getCaseSuitBySuit(suitKey);
    }

    @RequestMapping("/getById")
    @ResponseBody
    public CaseSuit getCaseSuitById(int id){
        return caseSuitService.getCaseSuitById(id);
    }

    @RequestMapping("/save")
    @ResponseBody
    public int saveCaseSuit(CaseSuit caseSuit){
        return caseSuitService.saveCaseSuit(caseSuit);
    }

    @RequestMapping("/update")
    @ResponseBody
    public int updateCaseSuit(CaseSuit caseSuit){
        return caseSuitService.updateCaseSuit(caseSuit);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public int deleteCaseSuit(int id){return caseSuitService.deleteCaseSuit(id);}

}
