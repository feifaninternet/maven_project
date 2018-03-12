package com.czh.core.aspect;

import com.czh.core.annotation.IgnoreToken;
import com.czh.core.constant.HttpMessageConstant;
import com.czh.core.utils.ResponseUtil;
import com.czh.model.cms.entity.Manager;
import com.czh.model.common.Token;
import com.czh.orm.mybatis.mini.TokenMapper;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static com.czh.core.constant.CustomConstant.CMS_TOKEN;
import static com.czh.core.constant.CustomConstant.MINI_TOKEN;

/**
 * @author xfan
 * @date Created on 2018/2/11 -- 16:19
 * @desc 全局 token 认证
 */
public class AuthTokenAspect {
    private AuthTokenAspect() {
    }

    public static Object validateToken(ProceedingJoinPoint proceedingJoinPoint, TokenMapper tokenMapper, String type) throws Throwable {
        //获取当前执行的方法
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        //判断当前执行的方法是否存在自定义的注解
        if (methodSignature.getMethod().isAnnotationPresent(IgnoreToken.class)) {
            //只需要过滤登录即可，所以考虑在登录方法上加上注解，存在注解的就不验证token，不存在注解的需要验证
            return proceedingJoinPoint.proceed();
        } else {
            //获取 request response 对象
            ServletRequestAttributes res = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = res.getRequest();
            HttpServletResponse response = res.getResponse();
            String token = request.getHeader("token");
            //map构建返回对象
            Map<String, String> map = new HashMap<>(1, 1);
            if (StringUtils.isBlank(token)) {

                //构建一个 springMVC 拦截器
                HandlerInterceptorAdapter handlerInterceptorAdapter = new HandlerInterceptorAdapter() {
                    @Override
                    //预处理，返回false时不会下传，往回传递，被拦截的方法不会执行
                    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                        return false;
                    }

                };
                handlerInterceptorAdapter.preHandle(request, response, proceedingJoinPoint);
                map.put("reason", "AuthToken is unavailable");
                return ResponseUtil.error(HttpStatus.PRECONDITION_FAILED, HttpMessageConstant.HTTP_MESSAGE_UNAUTHORIZED,"AuthToken is unavailable");
            } else {
                //存在token
                String userUuid = null;
                Long createdAt = null;
                Integer expiresIn = null;
                if (type.equals(MINI_TOKEN)) {
                    userUuid = tokenMapper.selectUuidByToken(token);
                    if (userUuid != null) {
                        Token tokenEntity = tokenMapper.selectTokenByUuid(userUuid);
                        createdAt = Long.parseLong(tokenEntity.getCreate_time());
                        expiresIn = tokenEntity.getExpires_in();
                    }
                } else if (type.equals(CMS_TOKEN)) {
                    Manager manager = tokenMapper.selectIdByToken(token);
                    if (manager != null) {
                        userUuid = Integer.toString(manager.getId());
                        createdAt = Long.parseLong(manager.getCreate_time());
                        expiresIn = manager.getExpires_in();
                    }
                }

                if (userUuid == null) {
                    map.put("reason", "AuthToken is unavailable");
                    return ResponseUtil.error(HttpStatus.UNAUTHORIZED, HttpMessageConstant.HTTP_MESSAGE_UNAUTHORIZED,"AuthToken is unavailable");
                }
                Long nowTime = System.currentTimeMillis();
                Long expiresTime = (long) expiresIn * 1000;
                if (nowTime - createdAt > expiresTime) {
                    return ResponseUtil.error(HttpStatus.UNAUTHORIZED, HttpMessageConstant.HTTP_MESSAGE_UNAUTHORIZED,"AuthToken is expiry");
                }
                return proceedingJoinPoint.proceed();
            }
        }
    }
}

