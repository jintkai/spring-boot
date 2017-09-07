package com.jon.common;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Aspect
@EnableAspectJAutoProxy
public class AOPTest {

    @Before("execution(* com.jon.project.dao.ProjectDao.insertProject(..))")
    public void test(){
        System.out.println("--------------------Before--------------------");
    }

    @After("execution(* com.jon.project.dao.ProjectDao.insertProject(..))")
    public void afterTest(){
        System.out.println("--------------------After--------------------");
    }
}
