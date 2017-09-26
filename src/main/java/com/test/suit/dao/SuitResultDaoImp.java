package com.test.suit.dao;

import com.jon.common.BaseDao;
import com.test.suit.domain.SuitResult;
import org.springframework.stereotype.Repository;

@Repository
public class SuitResultDaoImp extends BaseDao {

    public int saveCaseResult(SuitResult result){
        return jdbcTemplate.update(
                "insert into qa_case_result_log(buildId,suitId,requestInfo,responseHeaders,responseBody," +
                        "responseCode,responseTime,assertLog,status,inserttime) values(?,?,?,?,?,?,?,?,?,now())",
                result.getBuildId(),result.getSuitid(),result.getRequestInfo(),result.getResponseHeaders(),result.getResponseBody(),
                result.getResponseCode(),result.getResponseTime(),result.getAssertLog(),result.getStatus());
    }
}
