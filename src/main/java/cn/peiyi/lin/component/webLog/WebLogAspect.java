package cn.peiyi.lin.component.webLog;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

@Aspect
public class WebLogAspect {
    private Logger log = LoggerFactory.getLogger(WebLogAspect.class);

    /**
     * 定义切入点，切入点为 service 下的所有函数
     */
    @Pointcut("execution(public * *..*service.*.*(..))")
    public void webLog() {}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        log.info("====>");
        log.info("进入方法：{}", joinPoint.getSignature().getDeclaringTypeName());
        log.info("参数: {}", Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "obj", pointcut = "webLog()")
    public void doAfterReturning(Object obj) throws Throwable {
        log.info("返回: {}", obj);
        log.info("<====");
    }
}
