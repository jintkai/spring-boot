package com.jon.common;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class BaseDao {

    @Autowired
    public JdbcTemplate jdbcTemplate;


}
