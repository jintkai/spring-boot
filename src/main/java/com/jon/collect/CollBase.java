package com.jon.collect;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CollBase {
    private List<KpiDetail> kpiList = new ArrayList<KpiDetail>();

    public void addKpi(KpiDetail KpiDetail){
        kpiList.add(KpiDetail);
    }
    public void addKpi(String unitId,String kpiId,String kpivalue){
        KpiDetail kpi = new KpiDetail(unitId,kpiId,kpivalue, new Date());
        addKpi(kpi);
    }
    @Override
    public String toString(){
        String s = "";
        for (KpiDetail kpi : kpiList)
        {
            s = s.concat(kpi.toString());
        }
        return s;
    }

    public List<KpiDetail> getKpiList() {
        return kpiList;
    }
}
