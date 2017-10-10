package com.test.suit.service;

import com.test.suit.dao.CaseSuitDaoImp;
import com.test.suit.dao.SuitResultDaoImp;
import com.test.suit.domain.Case;
import com.test.suit.domain.CaseSuit;
import com.test.suit.domain.SuitResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CaseSuitService {

    @Autowired
    CaseSuitDaoImp caseSuitDaoImp;

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

}
