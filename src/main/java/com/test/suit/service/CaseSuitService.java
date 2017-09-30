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

    @Deprecated
    public List<CaseSuit> getCaseBySuit(String suitId){
        return caseSuitDaoImp.getCaseSuitBySuit(suitId);
    }

    public void saveCaseResultLog(SuitResult result){
        //caseSuitDaoImp.saveCaseResultLog(result);
        suitResultDaoImp.saveCaseResult(result);
    }

    public List<CaseSuit> getCaseBySuitAndBuildID(String suitKey,int buildId){
        return caseSuitDaoImp.getClassUitBySuitAndBuildId(suitKey,buildId);
    }

    public List<SuitResult> getSuitCaseResultByID(int suitId,int buildId){
        return suitResultDaoImp.findCaseResultByID(suitId,buildId);
    }

}
