package com.czh.mini.aspect;

import com.czh.core.aspect.AuthTokenAspect;
import com.czh.core.constant.CustomConstant;
import com.czh.orm.mybatis.mini.TokenMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * @author xfan
 * @date Created on 2018/3/6 -- 14:30
 * @desc
 */
@Aspect
@Component
public class MiniTokenAspect {

    @Resource
    private TokenMapper tokenMapper;

    @Pointcut("execution(* com.java.web.controller.*Controller.*(..))")
    private void pointcut() {
    }

    @Around("pointcut()")
    public Object before(ProceedingJoinPoint joinPoint) throws Throwable {
        return AuthTokenAspect.validateToken(joinPoint, tokenMapper, CustomConstant.MINI_TOKEN);
    }
}
