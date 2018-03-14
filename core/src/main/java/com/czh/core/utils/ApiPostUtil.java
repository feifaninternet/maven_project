package com.czh.core.utils;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.czh.core.constant.CustomConstant.RESPONSE_UNAUTHORIZED;

/**
 * @author xfan
 * @date Created on 2018/2/11 -- 17:50
 * @desc 封装 HttpPost 请求
 */
public class ApiPostUtil {

    private static final String CONTENT_TYPE = "application/json";

    @SuppressWarnings("resource")
    public static String post(String json, HttpEntityEnclosingRequestBase post) {

        post.setHeader("Content-Type", CONTENT_TYPE);

        StringEntity s = new StringEntity(json, "utf-8");
        s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, CONTENT_TYPE));
        post.setEntity(s);

        CloseableHttpClient client = ApiPostUtil.createDefault();
        StringBuilder sb = new StringBuilder();
        String result = "";
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = client.execute(post);
            InputStream inStream = httpResponse.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_UNAUTHORIZED) {
                return RESPONSE_UNAUTHORIZED;
            }
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            inStream.close();

            result = sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpResponse != null) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    /**
     * Creates {@link CloseableHttpClient} instance with default
     * configuration.
     */
    public static CloseableHttpClient createDefault() {
        return HttpClientBuilder.create().build();
    }
}
