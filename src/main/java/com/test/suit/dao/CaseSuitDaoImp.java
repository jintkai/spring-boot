package com.test.suit.dao;

import com.jon.common.BaseDao;
import com.test.suit.domain.Case;
import com.test.suit.domain.CaseSuit;
import com.test.suit.domain.SuitResult;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CaseSuitDaoImp extends BaseDao {


    public List<CaseSuit> getCaseSuitBySuit(String suitKey) {
        return jdbcTemplate.query("select id,suitKey,suitName,caseid,requestType,requestUrl,requestHeader," +
                        "requestParameters,requestOrder,requestDependent,caseResult,assertExp,buildId from qa_suitcase where suitKey = ? order by requestOrder asc",
                new CaseSuitRowMapper(), suitKey);
    }

    public List<CaseSuit> getClassUitBySuitAndBuildId(String suitKey, int buildId) {
        return jdbcTemplate.query("select id,suitKey,suitName,caseid,requestType,requestUrl,requestHeader," +
                        "requestParameters,requestOrder,requestDependent,caseResult,assertExp,buildId from qa_suitcase where suitKey =? and buildId=? order by requestOrder desc",
                new CaseSuitRowMapper(), suitKey, buildId);
    }

    public CaseSuit getCaseSuitById(int id) {
        List<CaseSuit> caseSuits = jdbcTemplate.query("select * from `qa_suitcase` where id=?", new CaseSuitRowMapper(), id);
        return caseSuits.get(0);
    }

    public int saveCaseSuit(CaseSuit caseSuit) {
        return jdbcTemplate.update("insert into qa_suitcase(suitKey,suitName," +
                        "caseid, requestType, requestUrl,requestHeader, requestParameters," +
                        "assertExp, requestOrder, requestDependent, caseResult, buildId) values(?,?,?,?,?,?,?,?,?,?,?,?)",
                caseSuit.getSuitKey(), caseSuit.getSuitName(), caseSuit.getCaseId(), caseSuit.getRequestType(), caseSuit.getRequestUrl(),
                caseSuit.getRequestHeader(), caseSuit.getRequestParamets(), caseSuit.getAssertExp(), caseSuit.getRequestOrder(),
                caseSuit.getRequestDependent(), caseSuit.getCaseResult(), caseSuit.getBuildId());
    }

    public int updateCaseSuit(CaseSuit caseSuit) {

        return jdbcTemplate.update("update qa_suitcase set suitKey=?,suitName=?," +
                        "caseid=?, requestType=?, requestUrl=?,requestHeader=?, requestParameters=?," +
                        "assertExp=?, requestOrder=?, requestDependent=?, caseResult=?, buildId=? where id=?",
                caseSuit.getSuitKey(), caseSuit.getSuitName(), caseSuit.getCaseId(), caseSuit.getRequestType(), caseSuit.getRequestUrl(),
                caseSuit.getRequestHeader(), caseSuit.getRequestParamets(), caseSuit.getAssertExp(), caseSuit.getRequestOrder(),
                caseSuit.getRequestDependent(), caseSuit.getCaseResult(), caseSuit.getBuildId(), caseSuit.getId());
    }

    public int deleteCaseSuit(int id) {
        return jdbcTemplate.update("delete from qa_suitcase where id =?", id);
    }

}

class CaseSuitRowMapper implements RowMapper{

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        CaseSuit caseSuit = new CaseSuit();
        try {
            caseSuit.setId(rs.getInt("id"));
            caseSuit.setSuitKey(rs.getString("suitKey"));
            caseSuit.setSuitName(rs.getString("suitName"));
            caseSuit.setCaseId(rs.getInt("caseid"));
            caseSuit.setRequestType(rs.getInt("requestType"));
            caseSuit.setRequestUrl(rs.getString("requestUrl"));
            caseSuit.setRequestHeader(rs.getString("requestHeader"));
            caseSuit.setRequestParamets(rs.getString("requestParameters"));
            caseSuit.setRequestOrder(rs.getInt("requestOrder"));
            caseSuit.setRequestDependent(rs.getString("requestDependent"));
            caseSuit.setCaseResult(rs.getInt("caseResult"));
            caseSuit.setAssertExp(rs.getString("assertExp"));
//            caseSuit.setBuildId(rs.getInt("buildId"));
            caseSuit.setSuitKey(rs.getString("suitKey"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return caseSuit;    }
}
