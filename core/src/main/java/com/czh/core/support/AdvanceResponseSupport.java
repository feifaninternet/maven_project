package com.czh.core.support;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author xfan
 * @date Created on 2018/3/13 -- 17:40
 * @desc 读流方式构建 response
 */
public class AdvanceResponseSupport {

    private static final Logger log = LoggerFactory.getLogger(AdvanceResponseSupport.class);

    public static void advanceResponse(ResponseEntity body) {
        ServletRequestAttributes res = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = res.getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setStatus(HttpStatus.BAD_REQUEST.value());

        OutputStream outputStream = null;

        try {
            outputStream = response.getOutputStream();
            String error = new Gson().toJson(body);
            log.error("请求参数错误 =============" + error);
            outputStream.write(error.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }
    }
}
