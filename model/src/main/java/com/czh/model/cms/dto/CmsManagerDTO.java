package com.czh.model.cms.dto;

import com.czh.model.cms.entity.Manager;
import com.czh.model.cms.entity.Permission;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author xfan
 * @date Created on 2018/3/1 -- 17:14
 * @desc
 */
public class CmsManagerDTO extends Manager {

    @Getter @Setter
    private List<Permission> permissions;
}
