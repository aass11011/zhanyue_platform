package com.zym.fastplatform.framework.advice;

import com.alibaba.fastjson.JSON;
import com.zym.fastplatform.system.annotation.SystemLogging;
import com.zym.fastplatform.system.enums.SystemLoggingLevel;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SystemLoggingAspect {
    /***
    * @author zhangym
    * @date 2024/9/26
    * @description 正常返回后通知：记录日志，日志内容为方法名称，方法参数和返回值
    */
    @AfterReturning(pointcut = "@annotation(systemLogging)", returning = "result")
    public void afterReturning(JoinPoint joinPoint, SystemLogging systemLogging,Object result){
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        SystemLoggingLevel level = systemLogging.level();
        String s = buildLogMessage(systemLogging,result,methodName,args);
        switch (level){
            case TRACE -> logger.trace(s);
            case DEBUG -> logger.debug(s);
            case INFO -> logger.info(s);
            case WARN -> logger.warn(s);
            case ERROR -> logger.error(s);
        }
    }
    /***
    * @author zhangym
    * @date 2024/9/26
    * @description
    */
    private static String buildLogMessage(SystemLogging systemLogging, Object result, String methodName, Object[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("调用方法：").append(methodName);
        if(systemLogging.logParams()){
            String argsJSON = JSON.toJSONString(args);
            sb.append(", 参数： ").append(argsJSON);
        }
        if(systemLogging.logResult()){
            String resultJson = JSON.toJSONString(result);
            sb.append("，返回值： ").append(resultJson);
        }
        return sb.toString();
    }
    /***
    * @author zhangym
    * @date 2024/9/26
    * @description 抛出异常后通知：记录日志
    */
    @AfterThrowing(pointcut = "@annotation(systemLogging)",throwing = "e")
    public void afterThrowing(JoinPoint joinPoint,SystemLogging systemLogging,Exception e){
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        SystemLoggingLevel level = systemLogging.level();
        String s = buildLogMessage(systemLogging, e, methodName, args);
        switch (level){
            case TRACE -> logger.trace(s);
            case DEBUG -> logger.debug(s);
            case INFO -> logger.info(s);
            case WARN -> logger.warn(s);
            case ERROR -> logger.error(s);
        }
    }

    private static String buildLogMessage(SystemLogging systemLogging, Exception e, String methodName, Object[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("调用方法：").append(methodName);
        if (systemLogging.logParams()) {
            // 将参数转为JSON字符串
            String argsJson = JSON.toJSONString(args);
            sb.append("，参数：").append(argsJson);
        }
        if (systemLogging.logException()) {
            sb.append("，异常信息：").append(e.getMessage());
        }
        return sb.toString();
    }
}
