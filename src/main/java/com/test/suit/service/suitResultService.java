package com.test.suit.service;

import com.test.suit.dao.SuitResultDaoImp;
import com.test.suit.domain.CaseSuit;
import com.test.suit.domain.SuitResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by ying.zhang on 17/10/7.
 */
@Component
public class SuitResultService {

    @Autowired
    SuitResultDaoImp suitResultDaoImp;

    public SuitResult getSuitResultById(int id){
        return suitResultDaoImp.getSuitResultById(id);
    }

    public List<SuitResult> getBySuitIdAndbuildId(int suitId, int buildId){
        return suitResultDaoImp.getBySuitIdAndbuildId(suitId,buildId);
    }

    public int saveSuitResult(SuitResult suitResult){
        return suitResultDaoImp.saveSuitResult(suitResult);
    }

    public int updateSuitResult(SuitResult suitResult){
        return suitResultDaoImp.updateSuitResult(suitResult);
    }

    public int deleteSuitResult(int id){
        return suitResultDaoImp.deleteSuitResult(id);
    }


}
