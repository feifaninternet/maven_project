package com.czh.core.annotation;

import java.lang.annotation.*;

/**
 * @author xfan
 * @date Created on 2018/3/14 -- 10:19
 * @desc Path and query 的自定义校验规则
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ParameterValid {

    Class<?> type();

    String msg();

    boolean request() default true;

    boolean isEmpty() default true;

    boolean isBlank() default true;

    boolean isNull() default true;

    boolean isPhone() default false;

    int min() default 0;

    int max() default 0;

    int[] section() default {0,0};

    boolean isMin() default false;

    boolean isMax() default false;

    boolean isSection() default false;

}
