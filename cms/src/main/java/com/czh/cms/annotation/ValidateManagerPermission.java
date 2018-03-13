package com.czh.cms.annotation;

import com.czh.core.enums.Permission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xfan
 * @date Created on 2018/3/6 -- 13:22
 * @desc
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateManagerPermission {
    Permission permission();
}
