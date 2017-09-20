package com.jon.collect.host;

import com.jon.collect.CollBase;
import com.jon.collect.KpiDetail;
import com.sun.management.OperatingSystemMXBean;
import org.springframework.stereotype.Component;
import org.testng.annotations.Test;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

@Component
public class CollLinuxHost implements CollLinuxHostMBean{


    public List<KpiDetail> getMemory(){
        List<KpiDetail> kpiDetails = new ArrayList<KpiDetail>();
        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory
                .getOperatingSystemMXBean();
        float totalMem = osmxb.getTotalPhysicalMemorySize()/1024f/1024f;
        float freeMem = osmxb.getFreePhysicalMemorySize()/1024f/1024f;
        float totalSwap = osmxb.getTotalSwapSpaceSize()/1024f/1024f;
        float freeSwap = osmxb.getFreeSwapSpaceSize()/1024f/1024f;
        CollBase collBase = new CollBase();
        String unitId = "10-10";
        collBase.addKpi(unitId,"PM-10-11-001", String.valueOf(totalMem));
        collBase.addKpi(unitId,"PM-10-11-002",String.valueOf(freeMem));
        collBase.addKpi(unitId,"PM-10-11-003",String.valueOf(totalSwap));
        collBase.addKpi(unitId,"PM-10-11-004",String.valueOf(freeSwap));
        return collBase.getKpiList();
    }


    @Override
    public List<KpiDetail> getBaseInfo() {
        List<KpiDetail> kpiDetails = new ArrayList<KpiDetail>();
        CollBase collBase = new CollBase();
        String unitId = "10-10";
        collBase.addKpi(unitId,"PM-10-10-001",System.getProperty("os.name"));
        collBase.addKpi(unitId,"PM-10-10-002",System.getProperty("os.version"));
        collBase.addKpi(unitId,"PM-10-10-003",System.getProperty("user.name"));
        collBase.addKpi(unitId,"PM-10-10-004",System.getProperty("java.version"));
        collBase.addKpi(unitId,"PM-10-10-005",(new Date(System.currentTimeMillis())).toString());
        System.out.println(collBase.toString());
        return collBase.getKpiList();
    }

    @Override
    public List<KpiDetail> getCpu() {
        List<KpiDetail> kpiDetails = new ArrayList<KpiDetail>();
        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory
                .getOperatingSystemMXBean();
        osmxb.getAvailableProcessors();
        int cpuCount =osmxb.getAvailableProcessors();

        return null;
    }


}
