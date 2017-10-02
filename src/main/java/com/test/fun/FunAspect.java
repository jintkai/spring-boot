package com.test.fun;

import com.test.caseassert.domain.AssertExp;
import com.test.suit.domain.SuitResult;
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
        args[1] = funService.resolveFun(tmp,null);
        if(null != args[2]) {

            tmp = (String) args[2];
            args[2] = funService.resolveFun(tmp,null);
        }
        if(null != args[3]) {

            tmp = (String) args[3];
            args[3] = funService.resolveFun(tmp,null);
        }
        Object retVal = pjp.proceed(args);
        return retVal;
    }

    @Pointcut("execution(* com.test.caseassert.service.AssertServer.assertResult(..))")
    public void assertExpPointcut(){}

    @Around("assertExpPointcut()")
    public Object assertResult( ProceedingJoinPoint pjp ) throws Throwable {
        Object[] args = pjp.getArgs();
        List<String> list = (List<String>) args[0];
        SuitResult suitResults = (SuitResult)args[1];
        List<String> tmp = new ArrayList<>();
        if (0 != args.length){
            tmp.clear();
            for (int i = 0;i< list.size();i++){
                String assertExp = list.get(i);
                String assertTmp = "{{"+assertExp+"}}";
                String s = funService.resolveFun(assertTmp,suitResults);
                assertExp = assertExp.replace(assertExp,s);
                tmp.add(assertExp);
            }
        }
        args[0] = tmp;
        Map<String,Object> retVal = (Map<String, Object>) pjp.proceed(args);
        return retVal;

    }

}
