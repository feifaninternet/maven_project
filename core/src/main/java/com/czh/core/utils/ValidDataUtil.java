package com.czh.core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * @author xfan
 * @date Created on 2018/3/14 -- 11:18
 * @desc 数据校验工具类
 */
public class ValidDataUtil {

    private ValidDataUtil(){}

    /**
     * 校验手机号码
     */
    public static boolean isMobile(String mobiles) {
        Pattern p = compile("^[1][3,4,5,6,7,8,9][0-9]{9}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
}
