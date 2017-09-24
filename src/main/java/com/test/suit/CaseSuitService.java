package com.test.suit;

import com.test.suit.CaseSuit;
import com.test.suit.CaseSuitDaoImp;
import com.test.suit.SuitResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CaseSuitService {

    @Autowired
    CaseSuitDaoImp caseSuitDaoImp;


    public List<CaseSuit> getCaseBySuit(String suitId){
        return caseSuitDaoImp.getCaseSuitBySuit(suitId);
    }

    public void saveCaseResultLog(SuitResult result){
        caseSuitDaoImp.saveCaseResultLog(result);
    }
}
