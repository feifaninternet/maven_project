package com.czh.core.aspect;

import com.czh.core.annotation.ParameterValid;
import com.czh.core.annotation.PathAndQueryParamValid;
import com.czh.core.constant.HttpMessageConstant;
import com.czh.core.support.AdvanceResponseSupport;
import com.czh.core.support.ParamValidSupport;
import com.czh.core.utils.ResponseUtil;
import javassist.NotFoundException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xfan
 * @date Created on 2018/3/14 -- 10:26
 * @desc
 */
@Aspect
@Component
public class PathAndQueryParamValidAspect {

    private static final Logger log = LoggerFactory.getLogger(PathAndQueryParamValidAspect.class);

    @Before("@annotation(pathAndQueryParamValid)")
    public void paramValid(JoinPoint point, PathAndQueryParamValid pathAndQueryParamValid){
        String className = point.getTarget().getClass().getName();
        String methodName = point.getSignature().getName();
        Object[] param = point.getArgs();

        try {
            List<String> errorList = ParamValidSupport.get().validate(className,methodName, ParameterValid.class,param);
            if (errorList != null){
                AdvanceResponseSupport.advanceResponse(ResponseUtil.error(HttpStatus.BAD_REQUEST, HttpMessageConstant.HTTP_MESSAGE_BAG_REQUEST,errorList));
            }
        } catch (NotFoundException | NoSuchMethodException | ClassNotFoundException e) {
            log.error("e-name：" + e.getClass().getName() + "： message：" + e.getMessage());
        }
    }
}
