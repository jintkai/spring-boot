package com.jon.project;

import com.jon.collect.host.CollLinuxHost;
import com.jon.collect.host.CollLinuxHostMBean;
import com.jon.collect.service.CollLinuxHostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(classes = com.jon.Config.class)
public class CollLinuxHostTest extends AbstractTestNGSpringContextTests {

    @Autowired
    CollLinuxHostService service;
    @Test
    public void test(){
        //service.collLinuxHostInfo();
    }
    @Test
    public void testMem(){
        //service.collLinuxHostMem();
    }
}
