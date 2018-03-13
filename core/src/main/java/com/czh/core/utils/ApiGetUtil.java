package com.czh.core.utils;

import com.czh.core.constant.CustomConstant;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author xfan
 * @date Created on 2018/2/11 -- 18:01
 * @desc 封装 HttpGet 请求
 */
public class ApiGetUtil {
    public static String get(HttpGet get) {

        CloseableHttpClient httpClient = ApiPostUtil.createDefault();
        CloseableHttpResponse response = null;
        String resultStr = "";
        try {
            response = httpClient.execute(get);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_UNAUTHORIZED) {
                return CustomConstant.RESPONSE_UNAUTHORIZED;
            }
            HttpEntity entity = response.getEntity();
            resultStr = EntityUtils.toString(entity, "UTF-8");
            return resultStr;
        } catch (Exception e) {
            e.printStackTrace();
            return resultStr;
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
