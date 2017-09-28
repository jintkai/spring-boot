package com.test.fun;

import com.test.caseassert.domain.AssertExp;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Aspect
@Component
public class FunAspect {
//    private static final Logger logger = (Logger) LoggerFactory.getLogger(FunAspect.class);

    @Autowired
    FunService funService;

    //@Pointcut("@annotation(com.jon.httpclient.HttpClientService)")
    @Pointcut("execution(* com.jon.httpclient.HttpClientService.sentRequest(..))")
    public void annotationPointcut(){
    }

    @Around("annotationPointcut()")
    public Object test(ProceedingJoinPoint pjp) throws Throwable{
        Object[] args = pjp.getArgs();
        String tmp = (String) args[1];
        args[1] = funService.resolveFun(tmp);
        if(null != args[2]) {

            tmp = (String) args[2];
            args[2] = funService.resolveFun(tmp);
            System.out.println("________________________" + args[2]);
        }
        if(null != args[3]) {

            tmp = (String) args[3];
            args[3] = funService.resolveFun(tmp);
            System.out.println("________________________" + args[3]);
        }
        Object retVal = pjp.proceed(args);
        return retVal;
    }

    @Pointcut("execution(* com.test.caseassert.service.AssertServer.assertResult(..))")
    public void assertExpPointcut(){}

    @Around("assertExpPointcut()")
    public Object assertResult( ProceedingJoinPoint pjp ) throws Throwable {
        Object[] args = pjp.getArgs();
        List<AssertExp> list = (List<AssertExp>) args[0];
        List<AssertExp> tmp = new ArrayList<>();
        if (0 != args.length){
            tmp.clear();
            for (int i = 0;i< list.size();i++){
                AssertExp assertExp = list.get(i);
                String s = funService.resolveFun(list.get(i).getMethodName());
                assertExp.setMethodName(s);
                s = funService.resolveFun(list.get(i).getParams());
                assertExp.setParams(s);
                tmp.add(assertExp);
            }
        }
        args[0] = tmp;
        Map<String,Object> retVal = (Map<String, Object>) pjp.proceed(args);
        return retVal;

    }

}
