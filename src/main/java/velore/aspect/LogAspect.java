package velore.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 日志切面类
 * 如果有需要,自定义切面类
 * @author Velore
 * @date 2022/4/11
 **/
@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LogManager.getLogger(LogAspect.class);

    @Around("execution(* *.controller.*(..))")
    public Object logControllerParam(ProceedingJoinPoint point) throws Throwable {
        String methodName = point.getSignature().getName();
        Object[] args = point.getArgs();
        StringBuilder builder = new StringBuilder().append(methodName).append("(");
        for(Object o : args){
            builder.append(o.toString());
        }
        logger.info(builder.append(")").toString());
        return point.proceed();
    }
}
