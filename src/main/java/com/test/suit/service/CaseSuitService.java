package com.test.suit.service;

import com.test.suit.dao.CaseSuitDaoImp;
import com.test.suit.dao.SuitResultDaoImp;
import com.test.suit.domain.Case;
import com.test.suit.domain.CaseSuit;
import com.test.suit.domain.SuitResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Component
public class CaseSuitService {

    @Autowired
    CaseSuitDaoImp caseSuitDaoImp;

    @Autowired
    SuitResultDaoImp suitResultDaoImp;

    public List<CaseSuit> getCaseBySuitAndBuildID(String suitId,int buildId){
        return caseSuitDaoImp.getClassUitBySuitAndBuildId(suitId,buildId);
    }


    public List<CaseSuit> getCaseSuitBySuit(String suitKey){
        return caseSuitDaoImp.getCaseSuitBySuit(suitKey);
    }

    public CaseSuit getCaseSuitById(int id){
        return caseSuitDaoImp.getCaseSuitById(id);
    }

    public int saveCaseSuit(CaseSuit caseSuit){
        return caseSuitDaoImp.saveCaseSuit(caseSuit);
    }

    public int updateCaseSuit(CaseSuit caseSuit){
        return caseSuitDaoImp.updateCaseSuit(caseSuit);
    }

    public int deleteCaseSuit(int id){
        return caseSuitDaoImp.deleteCaseSuit(id);
    }

    public List<CaseSuit> getCaseBySuit(String suitKey,boolean isNew){
        List<CaseSuit>  list = caseSuitDaoImp.getCaseSuitBySuit(suitKey);
        int lastBuild = getLastBuild(suitKey);
        if (isNew)
            lastBuild+=1;
        for (CaseSuit suit:list)
            suit.setBuildId(lastBuild);
        return list;
    }


    public List<SuitResult> getSuitCaseResultByID(int suitId,int buildId){
        return suitResultDaoImp.findCaseResultByID(suitId,buildId);
    }

    public int getLastBuild(String suitKey){
        return suitResultDaoImp.getLastBuildID(suitKey);
    }


    public void saveResult(SuitResult result,boolean isNew){
        if (!isNew){
            suitResultDaoImp.updateCaseResult(result);
        }else
            suitResultDaoImp.savaCaseResult(result);
        suitResultDaoImp.saveCaseResultHis(result);
    }

    public List getBuildResult(String suitKey,int buildid){
       return suitResultDaoImp.findBuildResult(suitKey,buildid);
    }

    //统计数据
    public void saveReport(String suitKey,String buildID){
        suitResultDaoImp.saveReport(suitKey,buildID);
    }
}
