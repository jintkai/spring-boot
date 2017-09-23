package com.test.suit;

import com.jon.common.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class SuitResultDaoImp extends BaseDao {

    public int saveCaseHistory(SuitResult result){
        return jdbcTemplate.update("insert into qa_case_result_log(suitGUID，detail,status,resultBody,resposeTime) values(?,?,?,?,?)",
                result.getSuitGuid(),result.getRequestParameter(),result.getStatus(),result.getResultBody(),result.getResponseTime());
    }
}
