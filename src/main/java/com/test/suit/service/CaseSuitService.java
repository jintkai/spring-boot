package com.test.suit.service;

import com.test.suit.dao.CaseSuitDaoImp;
import com.test.suit.dao.SuitResultDaoImp;
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

/*    @Deprecated
    public List<CaseSuit> getCaseBySuit(String suitId){
        return caseSuitDaoImp.getCaseSuitBySuit(suitId);
    }*/
/*
    public void saveCaseResultLog(SuitResult result){
        //caseSuitDaoImp.saveCaseResultLog(result);
        suitResultDaoImp.saveCaseResultHis(result);
    }*/


    public List<CaseSuit> getCaseBySuit(String suitKey,boolean isNew){
        List<CaseSuit>  list = caseSuitDaoImp.getCaseSuitBySuit(suitKey);
        int lastBuild = getLastBuild(suitKey);
        if (isNew)
            lastBuild+=1;
        for (CaseSuit suit:list)
            suit.setBuildId(lastBuild);
        return list;
    }

/*    @Deprecated
    public List<CaseSuit> getCaseBySuitAndBuildID(String suitKey,int buildId){
        return caseSuitDaoImp.getClassUitBySuitAndBuildId(suitKey,buildId);
    }*/

    public List<SuitResult> getSuitCaseResultByID(int suitId,int buildId){
        return suitResultDaoImp.findCaseResultByID(suitId,buildId);
    }

    public int getLastBuild(String suitKey){
        return suitResultDaoImp.getLastBuildID(suitKey);
    }


/*    public void runCase(SuitResult result){
        if (result.getBuildId() == getLastBuild(result.getSuitKey())){
            suitResultDaoImp.updateCaseResult(result);
        }else
            suitResultDaoImp.savaCaseResult(result);
        suitResultDaoImp.saveCaseResultHis(result);
    }*/

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
