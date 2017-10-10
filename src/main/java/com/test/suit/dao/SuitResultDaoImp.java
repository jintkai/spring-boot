package com.test.suit.dao;

import com.jon.common.BaseDao;
import com.test.suit.domain.SuitReport;
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
  
    public int saveCaseResultHis(SuitResult result){
        return jdbcTemplate.update(
                "insert into qa_case_result_his(buildId,suitId,requestInfo,responseHeaders,responseBody," +
                        "responseCode,responseTime,assertLog,status,inserttime) values(?,?,?,?,?,?,?,?,?,now())",
                result.getBuildId(),result.getSuitid(),result.getRequestInfo(),result.getResponseHeaders(),result.getResponseBody(),
                result.getResponseCode(),result.getResponseTime(),result.getAssertLog(),result.getStatus());

    }

    public int updateCaseResult(SuitResult result){
        return jdbcTemplate.update("update qa_case_result set responseHeaders=? , responseCode=? ," +
                " responseBody=?,responseTime=?,assertLog=?,status=?,updatetime=now() where suitid=? and buildid = ?",
                result.getResponseHeaders(),result.getResponseCode(),result.getResponseBody(),result.getResponseTime(),
                result.getAssertLog(),result.getStatus(),
                result.getSuitid(),result.getBuildId());
    }

    public int savaCaseResult(SuitResult result){
        return jdbcTemplate.update(
                "insert into qa_case_result(suitKey,buildId,suitId,responseHeaders,responseBody," +
                        "responseCode,responseTime,assertLog,status,inserttime) values(?,?,?,?,?,?,?,?,?,now())",
                result.getSuitKey(),result.getBuildId(),result.getSuitid(),result.getResponseHeaders(),result.getResponseBody(),
                result.getResponseCode(),result.getResponseTime(),result.getAssertLog(),result.getStatus());
    }

    public List<SuitResult> findCaseResultByID(int suitid,int buildID){
        return jdbcTemplate.query("select id,buildid,suitid,responseCode,responseBody,responseTime,status" +
                        " from qa_case_result where suitid =? and buildid=?",
                new SuitResultRowMapper(), suitid,buildID);
    }

    public List<SuitReport> findBuildResult(String suitKey , int buildId){
        return jdbcTemplate.query("select s.suitName,s.requestOrder,t.* from (select r.suitKey,r.id,r.buildid,r.suitid,r.responseCode,r.responseTime,r.status from qa_case_result r where r.suitKey= ? and r.buildId = ? ) t left join qa_suitcase s on t.suitid = s.id order by s.requestOrder asc;",
                new SuitReportRowMapper(),suitKey,buildId);
    }

    //获取最大suitKey的最大构建id
    public int getLastBuildID(String suitKey){
        List<Integer> buildIds = jdbcTemplate.query("select max(buildId) from qa_case_result where suitKey=?",
                new RowMapper<Integer>() {
                    @Override
                    public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                        return resultSet.getInt(1);
                    }
                },
                suitKey);
        if (null == buildIds)
            return 0;
        return buildIds.get(0);
    }

    //获取某一个suitid的最大构建id
    public int getLastBuildIdBySuitId(String suitId){
        List<Integer> buildIds = jdbcTemplate.query("select max(buildId) from qa_case_result where suitId=?",
                new RowMapper<Integer>() {
                    @Override
                    public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                        return resultSet.getInt(1);
                    }
                },
                suitId);
        if (null == buildIds)
            return 0;
        return buildIds.get(0);
    }

    //每次构建后统计数据
    public void saveReport(String suitKey,String buildID){
        List<SuitResult> list = jdbcTemplate.query("select id,buildid,suitid,responseCode,responseBody,responseTime,status" +
                        " from qa_case_result where suitKey =? and buildid=?",
                new SuitResultRowMapper(), suitKey,buildID);
        jdbcTemplate.update("delete from qa_suit_report where suitKey =? and buildid=?",suitKey,buildID);
        int success = 0;
        int fail = 0;
        int unExecution = 0;
        int total = list.size();
        for (SuitResult suitResult:list){
            switch (Integer.valueOf(suitResult.getStatus())){
                case 1:
                    success+=1;
                    break;
                case 2:
                    fail+=1;
                    break;
                default:
                    unExecution+=1;
                    break;
            }
        }

        jdbcTemplate.update("insert into qa_suit_report(suitKey,buildId,success,fail,unExecution,total,inserttime) values (?,?,?,?,?,?,now())",
                suitKey,buildID,success,fail,unExecution,total);
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



class SuitReportRowMapper implements RowMapper{
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        SuitReport suitReport = new SuitReport();
        suitReport.setId(resultSet.getString("id"));
        suitReport.setSuitId(resultSet.getString("suitId"));
        suitReport.setSuitKey(resultSet.getString("suitKey"));
        suitReport.setSuitName(resultSet.getString("suitName"));
        suitReport.setBuildId(resultSet.getString("buildId"));
        suitReport.setResponseCode(resultSet.getString("responseCode"));
        suitReport.setResponseTime(resultSet.getString("responseTIme"));
        suitReport.setStatus(resultSet.getString("status"));
        suitReport.setRequestOrder(resultSet.getString("requestOrder"));
        return suitReport;
    }
}


