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

    public SuitResult getSuitResultById(int id) {
        List<SuitResult> suitResults = jdbcTemplate.query("select * from qa_case_result where id=?", new SuitResultRowMapper(), id);
        return suitResults.get(0);
    }

    public List<SuitResult> getBySuitIdAndbuildId(int suitId,int buildId) {
        List<SuitResult> suitResults = jdbcTemplate.query("select * from qa_case_result where suitid=? and buildId=? order by updatetime ", new SuitResultRowMapper(), suitId,buildId);
        return suitResults;
    }

    public int saveSuitResult(SuitResult suitResult){
        return jdbcTemplate.update("insert into qa_case_result (buildId, suitid, suitKey, responseHeaders, responseCode, "+
                "responseBody, responseTime, assertLog, status,inserttime,updatetime) values(?,?,?,?,?,?,?,?,?," +
                "now(),now())", suitResult.getBuildId(), suitResult.getSuitid(),suitResult.getSuitKey(),suitResult.getResponseHeaders(),
                suitResult.getResponseCode(), suitResult.getResponseBody(),suitResult.getResponseTime(),
                suitResult.getAssertLog(), suitResult.getStatus());
    }


    public int updateSuitResult(SuitResult suitResult){
        return jdbcTemplate.update("update qa_case_result set buildId=?,suitid=?,suitKey=?,"+
                "responseHeaders=?, responseCode=?, responseBody=?,responseTime=?, " +
                "assertLog=?,status=?, updatetime=now() where id=?",
                suitResult.getBuildId(),suitResult.getSuitid(),suitResult.getSuitKey(),suitResult.getResponseHeaders(),
                suitResult.getResponseCode(),suitResult.getResponseBody(),suitResult.getResponseTime(),
                suitResult.getAssertLog(),suitResult.getStatus(),suitResult.getId());
    }

    public int deleteSuitResult(int id){
        return jdbcTemplate.update("delete from qa_case_result where id =?",id);
    }
}

class SuitResultRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        SuitResult suitResult = new SuitResult();
        try {
            suitResult.setId(rs.getInt("id"));
            suitResult.setSuitid(rs.getInt("suitid"));
            suitResult.setBuildId(rs.getInt("buildId"));
            suitResult.setSuitKey(rs.getString("suitKey"));
            suitResult.setResponseHeaders(rs.getString("responseHeaders"));
            suitResult.setResponseBody(rs.getString("responseBody"));
            suitResult.setResponseCode(rs.getString("responseCode"));
            suitResult.setResponseTime(rs.getFloat("responseTime"));
            suitResult.setAssertLog(rs.getString("assertLog"));
            suitResult.setStatus(rs.getInt("status"));
            suitResult.setInsertTime(rs.getTime("inserttime"));
            suitResult.setUpdateTime(rs.getTime("updatetime"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suitResult;
    }
}