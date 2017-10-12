package com.test.report;

import com.jon.common.BaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
public class SuitReportDao  extends BaseDao{
    public final static Logger LOG = LoggerFactory.getLogger(SuitReportDao.class);

    List<SuitBuildRecord> getSuitBuildRecordList(String suitKey){

        List<SuitBuildRecord> list = jdbcTemplate.query("select suitKey,buildId,inserttime,success,fail,unExecution,total,success/total as successPercent from qa_suit_report where suitKey=? order by buildId",
                new SuitBuildRecordRowMap(),suitKey);
        return list;
    }
}

class SuitBuildRecordRowMap implements RowMapper{

    @Override
    public SuitBuildRecord mapRow(ResultSet resultSet, int i) throws SQLException {
        SuitBuildRecord suitBuildRecord = new SuitBuildRecord();
        suitBuildRecord.setSuitKey(resultSet.getString("suitKey"));
        suitBuildRecord.setBuildid(resultSet.getInt("buildId"));
        suitBuildRecord.setInsertTime(resultSet.getTime("inserttime"));
        suitBuildRecord.setSuccess(resultSet.getInt("success"));
        suitBuildRecord.setFail(resultSet.getInt("fail"));
        suitBuildRecord.setUnExecution(resultSet.getInt("unExecution"));
        suitBuildRecord.setSuccessPercent(resultSet.getFloat("successPercent"));
        suitBuildRecord.setTotal(resultSet.getInt("total"));
        return suitBuildRecord;
    }
}
