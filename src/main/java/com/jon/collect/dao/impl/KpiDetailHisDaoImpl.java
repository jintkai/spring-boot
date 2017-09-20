package com.jon.collect.dao.impl;

import com.jon.collect.KpiDetail;
import com.jon.common.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class KpiDetailHisDaoImpl extends BaseDao{

    public int insertKpiDetail(KpiDetail kpiDetail) {

            return jdbcTemplate.update("insert into kpidetail_his (unitid,kpiid,kpivalue,clltime,instime) values (?,?,?,?,SELECT SYSTIMESTAMP FROM dual)",
                    kpiDetail.getUnitId(), kpiDetail.getKpiId(), kpiDetail.getKpiValue(), kpiDetail.getCollTime());

    }
}
