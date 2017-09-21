package com.jon.collect.dao.impl;

import com.jon.collect.KpiDetail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KpiRowMapper implements RowMapper<KpiDetail> {

    @Override
    public KpiDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        if (null == rs)
            return null;
        return new KpiDetail(rs.getString("unitid"),rs.getString("kpiid"),rs.getString("kpivalue"),
                rs.getDate("clltime"),rs.getDate("instime"));
    }
}
