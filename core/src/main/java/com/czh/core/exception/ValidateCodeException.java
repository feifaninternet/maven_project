package com.czh.core.exception;


/**
 * 验证码异常
 *
 * @author ： CatalpaFlat
 * @date ：Create in 14:30 2017/12/8
 */
public class ValidateCodeException extends Exception {

    public ValidateCodeException(String msg, Throwable t) {
        super(msg, t);
    }

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
