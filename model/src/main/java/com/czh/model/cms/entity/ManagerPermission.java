package com.czh.model.cms.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xfan
 * @date Created on 2018/3/1 -- 15:58
 * @desc 管理员权限
 */
public class ManagerPermission {
    @Getter @Setter
    private Integer id;

    @Getter @Setter
    private String permission_content;

    @Getter @Setter
    private Integer manager_id;
}
