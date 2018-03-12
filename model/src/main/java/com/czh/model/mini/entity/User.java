package com.czh.model.mini.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xfan
 * @date Created on 2018/2/11 -- 15:40
 * @desc 用户实体
 */
public class User {

    @Getter @Setter
    private String uuid;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String phone;

    @Getter @Setter
    private String created_at;

    @Getter @Setter
    private String open_id;

    public User(){}

    /**
     * 此构造方法用于用户登录
     */
    public User(String uuid,String name, String createdAt, String openId, String phone) {
        this.uuid = uuid;
        this.name = name;
        this.created_at = createdAt;
        this.open_id = openId;
        this.phone = phone;
    }

    /**
     * 此构造方法用于手机号注册插入新用户
     */
    public User(String uuid, String createdAt, String phone){
        this.uuid = uuid;
        this.created_at = createdAt;
        this.phone = phone;
    }
}
