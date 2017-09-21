package com.jon.common;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;


public class BaseDao {

    @Autowired
    public JdbcTemplate jdbcTemplate;


}
