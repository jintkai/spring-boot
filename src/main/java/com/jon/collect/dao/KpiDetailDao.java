package com.jon.collect.dao;

import com.jon.collect.KpiDetail;

public interface KpiDetailDao {
    public int insertKpiDetail(KpiDetail kpiDetail);
    public KpiDetail selectKpiDetail(KpiDetail kpiDetail);
}
