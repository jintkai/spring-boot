package com.jon.collect.host;

import com.sun.management.OperatingSystemMXBean;
import org.testng.annotations.Test;
import java.lang.management.ManagementFactory;

public class HostCollect {

    Runtime runtime = Runtime.getRuntime();

    OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory
            .getOperatingSystemMXBean();
    @Test
    public void test(){
        Long totalVMem = runtime.totalMemory()/1024/1024;
        Long freeVMem = runtime.freeMemory()/1024/1024;
        int availCpu = runtime.availableProcessors();
        System.out.println("total:"+totalVMem+",free:"+freeVMem+",availableCpu:"+availCpu);
        String sysName = osmxb.getName();
        Long totalMem = osmxb.getTotalPhysicalMemorySize()/1024/1024;
        Long freeMem = osmxb.getFreePhysicalMemorySize()/1024/1024;
        Double cpuLoad = osmxb.getProcessCpuLoad();
        Long cpuTime = osmxb.getProcessCpuTime();

        System.out.println("SysName:"+sysName+",totalMem:"+totalMem+",freeMem:"+freeMem);
        System.out.println("CpuTime:"+cpuTime+",CpuLoad:"+cpuLoad+",cp");

    }

}
