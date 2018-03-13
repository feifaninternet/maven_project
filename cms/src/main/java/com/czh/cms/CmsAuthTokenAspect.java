package com.czh.cms;

import com.czh.orm.mybatis.mini.TokenMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static com.czh.core.constant.CustomConstant.CMS_TOKEN;

/**
 * @author xfan
 * @date Created on 2018/3/6 -- 14:20
 * @desc
 */
@Aspect
@Component
public class CmsAuthTokenAspect {
    @Resource
    private TokenMapper tokenMapper;

    @Pointcut("execution(* com.java.web.controller.cms.*Controller.*(..))")
    private void pointcut() {
    }

    @Around("pointcut()")
    public Object before(ProceedingJoinPoint joinPoint) throws Throwable {
        return AuthTokenAspect.validateToken(joinPoint, tokenMapper, CustomConstant.CMS_TOKEN);
    }
}
