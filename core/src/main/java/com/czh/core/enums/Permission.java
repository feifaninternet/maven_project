package com.czh.core.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xfan
 * @date Created on 2018/3/6 -- 14:12
 * @desc
 */
public enum Permission {
    CMS_MANAGER(0,"管理员管理"),
    CMS_USER(1,"用户管理"),
    CMS_DEVICE(2,"抽纸盒管理"),
    CMS_RECHARGE(3,"充值管理"),
    CMS_GIFT_CARD(4,"礼卡管理"),
    CMS_DEVICE_FAULT(5,"故障反馈"),
    CMS_MESSAGE(6,"消息通知");

    @Getter @Setter
    private int value;

    @Getter @Setter
    private String msg;

    Permission(int value,String msg){
        this.value = value;
        this.msg = msg;
    }

    Permission(int value){
        this.value = value;
    }
}
