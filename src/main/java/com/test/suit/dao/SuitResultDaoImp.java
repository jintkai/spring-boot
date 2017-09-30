package com.test.suit.dao;

import com.jon.common.BaseDao;
import com.test.suit.domain.SuitResult;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SuitResultDaoImp extends BaseDao {

    public int saveCaseResult(SuitResult result){
        return jdbcTemplate.update(
                "insert into qa_case_result_log(buildId,suitId,requestInfo,responseHeaders,responseBody," +
                        "responseCode,responseTime,assertLog,status,inserttime) values(?,?,?,?,?,?,?,?,?,now())",
                result.getBuildId(),result.getSuitid(),result.getRequestInfo(),result.getResponseHeaders(),result.getResponseBody(),
                result.getResponseCode(),result.getResponseTime(),result.getAssertLog(),result.getStatus());
    }

    public List<SuitResult> findCaseResultByID(int suitid,int buildID){
        return jdbcTemplate.query("select id,buildid,suitid,responseCode,responseBody,responseTime,status" +
                        " from qa_case_result_log where suitid =? order by buildid",
                new SuitResultRowMapper(), suitid);
    }
}
class SuitResultRowMapper implements RowMapper{

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        SuitResult suitResult = new SuitResult();
        suitResult.setId(rs.getInt("id"));
        suitResult.setBuildId(rs.getInt("buildid"));
        suitResult.setSuitid(rs.getString("suitid"));
        suitResult.setResponseCode(rs.getString("responseCOde"));
        suitResult.setResponseBody(rs.getString("responseBody"));
        //suitResult.setInsertTime(rs.getTime("responseTime"));
        suitResult.setStatus(rs.getInt("status"));

        return suitResult;
    }
}

