package com.jon.collect.host;

import com.jon.collect.KpiDetail;

import java.util.List;

public interface CollLinuxHostMBean {

    public List<KpiDetail> getMemory();

    public List<KpiDetail> getBaseInfo();

    public List<KpiDetail> getCpu();
}
