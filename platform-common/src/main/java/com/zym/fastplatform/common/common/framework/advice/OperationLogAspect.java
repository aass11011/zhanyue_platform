package com.zym.fastplatform.common.common.framework.advice;

import com.alibaba.fastjson.JSON;
import com.zym.fastplatform.common.common.util.HttpUtil;
import com.zym.fastplatform.common.common.util.SecurityUtils;
import com.zym.fastplatform.common.system.annotation.OperationLogging;
import com.zym.fastplatform.common.system.entity.OperationLog;
import com.zym.fastplatform.common.system.service.OperationLogService;
import jakarta.annotation.Resource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
@Aspect
public class OperationLogAspect {
    @Resource
    private OperationLogService operationLogService;

    private final ThreadLocal<LocalDateTime> startTime = new ThreadLocal<>();

    @Before("@annotation(operationLogging)")
    public void before(OperationLogging operationLogging){
        startTime.set(LocalDateTime.now());
    }

    @AfterReturning(pointcut = "@annotation(operationLogging)",returning = "result")
    public void afterReturning(JoinPoint joinPoint,OperationLogging operationLogging,Object result){
        String resultStr = JSON.toJSONString(result);
        buildAndSaveLog(joinPoint,operationLogging,resultStr);
    }

    @AfterThrowing(pointcut = "@annotation(operationLogging)",throwing = "e")
    public void afterThrowing(JoinPoint joinPoint,OperationLogging operationLogging,Exception e){
        String resultStr = e.getMessage();
        if(resultStr.length()>2000){
            resultStr = resultStr.substring(0, 2000);
        }
        buildAndSaveLog(joinPoint,operationLogging,resultStr);
    }
    @Transactional(rollbackFor = Exception.class)
    public void buildAndSaveLog(JoinPoint joinPoint, OperationLogging operationLogging, String resultStr) {
        OperationLog operationLog = new OperationLog();
        operationLog.setDescription(operationLogging.description());
        HttpUtil.getRequest().map(HttpUtil::getIpAddress).ifPresent(operationLog::setIp);
        operationLog.setMethod(joinPoint.getSignature().getName());
        operationLog.setOperationType(operationLogging.type().ordinal());
        operationLog.setParams(JSON.toJSONString(joinPoint.getArgs()));
        operationLog.setResult(resultStr);
        operationLog.setTime(startTime.get());
        operationLog.setDuration(Duration.between(startTime.get(),LocalDateTime.now()).toMillis());
        operationLog.setUsername(SecurityUtils.getLoginUsername());
        operationLogService.save(operationLog);
    }


}
