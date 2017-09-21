package com.jon.collect;


import java.util.Date;

public class KpiDetail {

    public KpiDetail(String unitId, String kpiId, String kpiValue, Date collTime) {
        this.unitId = unitId;
        this.kpiId = kpiId;
        this.kpiValue = kpiValue;
        this.collTime = collTime;
    }
    public KpiDetail(String unitId, String kpiId, String kpiValue, Date collTime,Date insTime) {
        this.unitId = unitId;
        this.kpiId = kpiId;
        this.kpiValue = kpiValue;
        this.collTime = collTime;
        this.insTime = insTime;
    }
    private String unitId;
    private String kpiId;
    private String kpiValue;
    private Date collTime;
    private Date insTime;


    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getKpiId() {
        return kpiId;
    }

    public void setKpiId(String kpiId) {
        this.kpiId = kpiId;
    }

    public String getKpiValue() {
        return kpiValue;
    }

    public void setKpiValue(String kpiValue) {
        this.kpiValue = kpiValue;
    }

    public Date getCollTime() {
        return collTime;
    }

    public void setCollTime(Date collTime) {
        this.collTime = collTime;
    }

    public Date getInsTime() {
        return insTime;
    }

    public void setInsTime(Date insTime) {
        this.insTime = insTime;
    }

    @Override
    public String toString(){
        String s = "";
        s = s.concat("unitId:"+unitId);
        s = s.concat(",kpiId:"+kpiId);
        s = s.concat(",kpiValue:"+kpiValue);
        s = s.concat(",collTime:"+collTime);
        return s;
    }
}
