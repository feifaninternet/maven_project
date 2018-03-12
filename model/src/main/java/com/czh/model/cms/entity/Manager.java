package com.czh.model.cms.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xfan
 * @date Created on 2018/2/11 -- 15:40
 * @desc 管理员实体
 */
public class Manager {

    @Getter @Setter
    private Integer id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String account;

    @Getter @Setter
    private String password;

    @Getter @Setter
    private String token;

    @Getter @Setter
    private String create_time;

    @Getter @Setter
    private Integer expires_in;

    public Manager(){}

    public Manager(String name, String account, String password){
        this.name = name;
        this.account = account;
        this.password = password;
    }
}
