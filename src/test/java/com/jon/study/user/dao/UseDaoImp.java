package com.jon.study.user.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class UseDaoImp implements UseDao{

    @Autowired
    DataSource dataSource;

    public void saveUse(String userName,String passwd){

    }

}
