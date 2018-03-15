package com.czh.cms.aspect;

import com.czh.cms.annotation.IgnorePermission;
import com.czh.cms.annotation.ValidateManagerPermission;
import com.czh.core.constant.HttpMessageConstant;
import com.czh.core.utils.ResponseUtil;
import com.czh.model.cms.entity.Manager;
import com.czh.model.cms.entity.ManagerPermission;
import com.czh.orm.mybatis.cms.CmsManagerMapper;
import com.czh.orm.mybatis.mini.TokenMapper;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xfan
 * @date Created on 2018/3/6 -- 14:53
 * @desc
 */
@Aspect
@Component
public class CmsPermissionAspect {

    @Resource
    private TokenMapper cmsTokenMapper;
    @Resource
    private CmsManagerMapper cmsManagerMapper;

    @Around("@annotation(validateManagerPermission)")
    public Object permissionValid(ProceedingJoinPoint proceedingJoinPoint, ValidateManagerPermission validateManagerPermission) throws Throwable {
        //获取当前执行的方法
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        //判断当前执行的方法是否存在自定义的注解
        if (methodSignature.getMethod().isAnnotationPresent(IgnorePermission.class)) {
            //只需要过滤登录即可，所以考虑在登录方法上加上注解，存在注解的就不验证token，不存在注解的需要验证
            return proceedingJoinPoint.proceed();
        } else {
            //获取 request response 对象
            ServletRequestAttributes res = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = res.getRequest();
            HttpServletResponse response = res.getResponse();
            String token = request.getHeader("token");

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
                return ResponseUtil.error(HttpStatus.PRECONDITION_FAILED, HttpMessageConstant.HTTP_MESSAGE_UNAUTHORIZED,"AuthToken is empty");
            }

            //存在token
            Manager manager = cmsTokenMapper.selectIdByToken(token);

            if (manager == null) {
                return ResponseUtil.error(HttpStatus.UNAUTHORIZED,HttpMessageConstant.HTTP_MESSAGE_UNAUTHORIZED,"Manager token error");
            }
            //根据管理员 id 检测是否拥有权限
            List<ManagerPermission> managerPermissions = cmsManagerMapper.selectPermissionByManagerId(manager.getId());
            List<String> permissions = new ArrayList<>();
            for(ManagerPermission permission : managerPermissions){
                permissions.add(permission.getPermission_content());
            }
            //校验
            if(managerPermissions.size() < 1){
                //构建一个 springMVC 拦截器
                HandlerInterceptorAdapter handlerInterceptorAdapter = new HandlerInterceptorAdapter() {
                    @Override
                    //预处理，返回false时不会下传，往回传递，被拦截的方法不会执行
                    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                        return false;
                    }

                };
                handlerInterceptorAdapter.preHandle(request, response, proceedingJoinPoint);
                return ResponseUtil.error(HttpStatus.CONFLICT,"没有权限进行设置","Permission error");
            }
            String adminPermission = validateManagerPermission.permission().getMsg();
            if(!permissions.contains(adminPermission)){
                //构建一个 springMVC 拦截器
                HandlerInterceptorAdapter handlerInterceptorAdapter = new HandlerInterceptorAdapter() {
                    @Override
                    //预处理，返回false时不会下传，往回传递，被拦截的方法不会执行
                    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                        return false;
                    }

                };
                handlerInterceptorAdapter.preHandle(request, response, proceedingJoinPoint);
                return ResponseUtil.error(HttpStatus.CONFLICT,"没有此权限","Permission error");
            }
            return proceedingJoinPoint.proceed();

        }
    }

}
