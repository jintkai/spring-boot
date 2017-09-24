package com.test.suit;

import com.jon.common.BaseDao;
import com.test.suit.CaseSuit;
import com.test.suit.SuitResult;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CaseSuitDaoImp extends BaseDao{

    public List<CaseSuit> getCaseSuitBySuit(String suitId){
        return jdbcTemplate.query("select id,suitKey,suitName,caseid,requestType,requestUrl,requestHeader," +
                "requestParameters,requestOrder,requestDependent,caseResult,assertExp from qa_suitcase where suitKey = ? order by requestOrder desc",
                new RowMapper<CaseSuit>() {
                    @Override
                    public CaseSuit mapRow(ResultSet rs, int rowNum) throws SQLException {
                        CaseSuit caseSuit = new CaseSuit();
                        caseSuit.setId(rs.getString("id"));
                        caseSuit.setSuitKey(rs.getString("suitKey"));
                        caseSuit.setSuitName(rs.getString("suitName"));
                        caseSuit.setCaseId(rs.getString("caseid"));
                        caseSuit.setRequestType(rs.getInt("requestType"));
                        caseSuit.setRequestUrl(rs.getString("requestUrl"));
                        caseSuit.setRequestHeader(rs.getString("requestHeader"));
                        caseSuit.setRequestParamets(rs.getString("requestParameters"));
                        caseSuit.setRequestOrder(rs.getInt("requestOrder"));
                        caseSuit.setRequestDependent(rs.getString("requestDependent"));
                        caseSuit.setCaseResult(rs.getInt("caseResult"));
                        caseSuit.setAssertExp(rs.getString("assertExp"));
                        return caseSuit;
                    }
                },suitId);
    }


    public int saveCaseResultLog(SuitResult result){
        return jdbcTemplate.update("insert into qa_case_result_log(suitId,detail,status,resultBody,responseTime,assertLog,resultStatus,inserttime) values(?,?,?,?,?,?,?,now())",
                result.getSuitGuid(),result.getRequestParameter(),result.getStatus(),result.getResultBody(),result.getResponseTime(),result.getAssertLog(),result.getResultStatus());
    }
}
