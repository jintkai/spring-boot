package com.test.suit.domain;

import java.util.Date;

public class Case {
    private int id;

    private int moduleId;

    private String name;

    private Byte requesttype;

    private String requesturl;

    private String requestheader;

    private String requestparamets;

    private String assertexp;

    private Byte requestorder;

    private String requestdependent;

    private Date inserttime;

    private Date updatetime;


    public Case() {
    }


    public Case(int id, int moduleId, String name, Byte requesttype, String requesturl, String requestheader, String requestparamets, String assertexp, Byte requestorder, String requestdependent, Date inserttime, Date updatetime) {
        this.id = id;
        this.moduleId = moduleId;
        this.name = name;
        this.requesttype = requesttype;
        this.requesturl = requesturl;
        this.requestheader = requestheader;
        this.requestparamets = requestparamets;
        this.assertexp = assertexp;
        this.requestorder = requestorder;
        this.requestdependent = requestdependent;
        this.inserttime = inserttime;
        this.updatetime = updatetime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getRequesttype() {
        return requesttype;
    }

    public void setRequesttype(Byte requesttype) {
        this.requesttype = requesttype;
    }

    public String getRequesturl() {
        return requesturl;
    }

    public void setRequesturl(String requesturl) {
        this.requesturl = requesturl == null ? null : requesturl.trim();
    }

    public String getRequestheader() {
        return requestheader;
    }

    public void setRequestheader(String requestheader) {
        this.requestheader = requestheader == null ? null : requestheader.trim();
    }

    public String getRequestparamets() {
        return requestparamets;
    }

    public void setRequestparamets(String requestparamets) {
        this.requestparamets = requestparamets == null ? null : requestparamets.trim();
    }

    public String getAssertexp() {
        return assertexp;
    }

    public void setAssertexp(String assertexp) {
        this.assertexp = assertexp == null ? null : assertexp.trim();
    }

    public Byte getRequestorder() {
        return requestorder;
    }

    public void setRequestorder(Byte requestorder) {
        this.requestorder = requestorder;
    }

    public String getRequestdependent() {
        return requestdependent;
    }

    public void setRequestdependent(String requestdependent) {
        this.requestdependent = requestdependent == null ? null : requestdependent.trim();
    }

    public Date getInserttime() {
        return inserttime;
    }

    public void setInserttime(Date inserttime) {
        this.inserttime = inserttime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }


}