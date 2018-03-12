package com.czh.model.common;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xfan
 * @date Created on 2018/2/11 -- 15:40
 * @desc token 实体
 */
public class Token {

    @Getter @Setter
    private Integer id;

    @Getter @Setter
    private String user_uuid;

    @Getter @Setter
    private String access_token;

    @Getter @Setter
    private String refresh_token;

    @Getter @Setter
    private Integer expires_in;

    @Getter @Setter
    private String create_time;

    public Token(){}

    /**
     * 此构造方法用于插入 token
     */
    public Token(String user_uuid,String access_token, String createdAt) {
        this.user_uuid = user_uuid;
        this.access_token = access_token;
        this.create_time = createdAt;
    }
}
