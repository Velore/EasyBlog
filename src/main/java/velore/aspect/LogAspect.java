package velore.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * 日志切面类
 * 如果有需要,自定义切面类
 * @author Velore
 * @date 2022/4/11
 **/
@Aspect
@Component
@Slf4j
public class LogAspect {

    public void logParam(JoinPoint point){
        String className = point.getSignature().getDeclaringTypeName();
        MethodSignature signature = (MethodSignature) point.getSignature();
        String[] argsName = signature.getParameterNames();
        String methodName = point.getSignature().getName();
        Object[] args = point.getArgs();
        StringBuilder builder = new StringBuilder()
                .append(className).append(".")
                .append(methodName).append("(\n\t");
        for(int i = 0 ,size = args.length;i<size;i++){
            builder.append(argsName[i]).append(":").append(args[i].toString());
            if (i != size - 1) {
                builder.append("\n\t");
            }
        }
        String info = builder.append("\n)").toString();
        System.out.println(info);
        log.warn(info);
    }

    @Before("execution(* velore.controller..*(..))")
    public void logControllerParam(JoinPoint point) {
        logParam(point);
    }

    @Before("execution(* velore.service..*(..))")
    public void logServiceParam(JoinPoint point) {
        logParam(point);
    }
}
