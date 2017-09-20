package com.jon.collect.service;

import com.jon.collect.KpiDetail;
import com.jon.collect.dao.KpiDetailDao;
import com.jon.collect.dao.impl.KpiDetailHisDaoImpl;
import com.jon.collect.host.CollLinuxHostMBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CollLinuxHostService {

    @Autowired
    CollLinuxHostMBean collLinuxHostMBean;
    @Autowired
    KpiDetailDao kpiDetailDao;
    @Autowired
    KpiDetailHisDaoImpl hisDao;

    public void collLinuxHostInfo(){
        List<KpiDetail> list = collLinuxHostMBean.getBaseInfo();
        for (KpiDetail kpi:list
             ) {
            kpiDetailDao.insertKpiDetail(kpi);
            hisDao.insertKpiDetail(kpi);
        }
    }
    public void collLinuxHostMem(){
        List<KpiDetail> list = collLinuxHostMBean.getMemory();
        for (KpiDetail kpi:list
                ) {
            kpiDetailDao.insertKpiDetail(kpi);
            hisDao.insertKpiDetail(kpi);
        }
    }
}
