package com.czh.core.aspect;

import com.czh.core.annotation.BodyParamValid;
import com.czh.core.constant.HttpMessageConstant;
import com.czh.core.support.AdvanceResponseSupport;
import com.czh.core.utils.ResponseUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xfan
 * @date Created on 2018/3/13 -- 17:51
 * @desc AOP 请求参数校验
 */
@Aspect
@Component
public class BodyParamValidAspect {

    private static final Logger log = LoggerFactory.getLogger(BodyParamValid.class);

    @Before("@annotation(bodyParamValid)")
    public void paramValid(JoinPoint point, BodyParamValid bodyParamValid){
        Object[] paramObj = point.getArgs();
        if(paramObj.length > 0){
            if (paramObj[1] instanceof BindingResult){
                BindingResult result = (BindingResult) paramObj[1];
                ResponseEntity errorMap = this.validRequestParams(result);
                if(errorMap != null){
                    log.error("请求体验证未通过");
                    AdvanceResponseSupport.advanceResponse(errorMap);
                }
            }
        }
    }

    /**
     * 校验
     */
    private ResponseEntity validRequestParams(BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            List<String> lists = new ArrayList<>();
            for (ObjectError objectError : allErrors) {
                lists.add(objectError.getDefaultMessage());
            }
            log.error("请求体不完整 =================" + lists);
            return ResponseUtil.error(HttpStatus.BAD_REQUEST, HttpMessageConstant.HTTP_MESSAGE_BAG_REQUEST, lists);
        }
        return null;
    }

}
