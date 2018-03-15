package com.czh.core.config;

import com.czh.model.common.BaseVO;
import com.google.gson.Gson;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xfan
 * @date Created on 2018/2/7 -- 15:43
 * @desc Custom logger
 */
@Component
@Aspect
public class LoggerConfig {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* *.*.*.service..*(..))")
    private void logPointCut() {
    }

    /**
     * logger before response
     *
     * @param joinPoint JoinPoint
     */
    @Before("logPointCut()")
    private void before(JoinPoint joinPoint) {
        Object[] objects = joinPoint.getArgs();
        // If the requested object is a request object in VO  ----  Logger jsonStr
        if (objects.length > 0 && objects.length == 1 && objects[0] !=null && objects[0].getClass().getSuperclass().equals(BaseVO.class)) {
            BaseVO baseVO = (BaseVO) objects[0];
            if (baseVO != null) {
                String paramsStr = new Gson().toJson(baseVO);
                logger.info("[Params] " + paramsStr);
            }
        } else if (objects.length == 0) {
            logger.info("[Params] null");
        } else {
            ServletRequestAttributes res = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (res != null) {
                HttpServletRequest request = res.getRequest();
                logger.info("[Params] " + request.getQueryString());
            }
        }
    }

    /**
     * logger after response
     *
     * @param joinPoint JoinPoint
     * @param response  ResponseEntity
     */
    @AfterReturning(pointcut = "logPointCut()", argNames = "joinPoint,response", returning = "response")
    private void afterReturn(JoinPoint joinPoint, ResponseEntity response) {
        logger.info(joinPoint + "Response: " + new Gson().toJson(response) + "\n");
    }

    @AfterThrowing(pointcut = "logPointCut()", throwing = "exception")
    private void afterThrow(JoinPoint joinPoint, Exception exception) {
        logger.info(joinPoint + "Exception: " + exception.getMessage());
    }
}
