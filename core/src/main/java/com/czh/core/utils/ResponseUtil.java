package com.czh.core.utils;

import net.sf.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author xfan
 * @date Created on 2018/3/6 -- 12:04
 * @desc
 */
public class ResponseUtil {
    private ResponseUtil() {
    }

    private static JSONObject responseJson;
    private static JSONObject responseErrorJson;

    static {
        responseJson = new JSONObject();
        responseErrorJson = new JSONObject();
    }

    public static ResponseEntity<Object> success(String msg) {
        responseJson.put("msg",msg);
        return obtainResponseEntity(HttpStatus.OK, responseJson);
    }

    public static ResponseEntity<Object> success(Object obj) {
        return obtainResponseEntity(HttpStatus.OK, obj);
    }

    public static ResponseEntity<Object> postSuccess(Object obj) {
        return obtainResponseEntity(HttpStatus.CREATED, obj);
    }

    public static ResponseEntity<Object> postSuccess(String msg) {
        responseJson.put("msg",msg);
        return obtainResponseEntity(HttpStatus.CREATED, responseJson);
    }

    public static ResponseEntity<Object> error(HttpStatus httpStatus, String msg,Object obj) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type",httpStatus.getReasonPhrase());
        jsonObject.put("reason",obj);
        jsonObject.put("message",msg);
        responseErrorJson.put("error",jsonObject);
        return obtainResponseEntity(httpStatus, responseErrorJson);
    }

    private static ResponseEntity<Object> obtainResponseEntity(HttpStatus httpStatus, Object response) {
        return new ResponseEntity<>(response, httpStatus);
    }
}
