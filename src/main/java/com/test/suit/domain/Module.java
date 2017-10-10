package com.test.suit.domain;

public class Module {
    private Integer id;

    private String module;

    private String group;

    public Module() {
    }

    public Module(Integer id, String module, String group) {
        this.id = id;
        this.module = module;
        this.group = group;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module == null ? null : module.trim();
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group == null ? null : group.trim();
    }
}