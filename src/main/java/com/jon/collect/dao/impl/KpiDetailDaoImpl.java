package com.jon.collect.dao.impl;

import com.jon.collect.KpiDetail;
import com.jon.collect.dao.KpiDetailDao;
import com.jon.common.BaseDao;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static javafx.scene.input.KeyCode.R;

@Repository
public class KpiDetailDaoImpl extends BaseDao implements KpiDetailDao{
    @Override
    public int insertKpiDetail(KpiDetail kpiDetail) {
        KpiDetail k = selectKpiDetail(kpiDetail);
        if ( null == k) {
            return jdbcTemplate.update("insert into kpidetail (unitid,kpiid,kpivalue,clltime,instime) values (?,?,?,?,SELECT SYSTIMESTAMP FROM dual)",
                    kpiDetail.getUnitId(), kpiDetail.getKpiId(), kpiDetail.getKpiValue(), kpiDetail.getCollTime());
        }else{
            return jdbcTemplate.update("update kpidetail set kpivalue=? ,clltime=? ,instime=(select sysdate from dual) where unitid=? and kpiid=?",
            kpiDetail.getKpiValue(),kpiDetail.getCollTime(),kpiDetail.getUnitId(),kpiDetail.getKpiId());
        }
    }


    @Override
    public KpiDetail selectKpiDetail(KpiDetail kpiDetail) {
        String sql = "select unitid,kpiid,kpivalue,clltime,instime from kpidetail where unitid=? and kpiid=?";
        List<KpiDetail> result = jdbcTemplate.query(sql,new KpiRowMapper(),kpiDetail.getUnitId(),kpiDetail.getKpiId());
        if (0 == result.size())
            return null;
        return result.get(0);
    }
}

