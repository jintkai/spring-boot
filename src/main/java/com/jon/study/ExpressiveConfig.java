package com.jon.study;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
//@PropertySource("classpath:/com/jon/study/app.properties")
public class ExpressiveConfig {

    @Autowired
    Environment env;

    public void test(){
        env.getProperty("jdbc.username");
        env.getActiveProfiles();
    }
}
