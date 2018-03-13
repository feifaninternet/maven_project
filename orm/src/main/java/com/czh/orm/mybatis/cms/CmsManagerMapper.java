package com.czh.orm.mybatis.cms;

import com.czh.model.cms.dto.CmsManagerDTO;
import com.czh.model.cms.entity.Manager;
import com.czh.model.cms.entity.ManagerPermission;
import com.czh.model.cms.entity.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xfan
 * @date Created on 2018/2/7 -- 15:24
 * @desc
 */
@Repository
public interface CmsManagerMapper {

    /**
     * 根据账号查询管理员
     * @param account 账号
     * @return 管理员
     */
    Manager selectManagerByAccount(@Param("account") String account);

    /**
     * 管理员列表
     * @param token token
     * @return 所有管理员
     */
    List<CmsManagerDTO> selectAllManager(@Param("token") String token);

    /**
     * 根据管理员名称或者账号查询
     * @param queryStr 查询条件 -- 名称或者账号
     * @return 管理员信息
     */
    List<CmsManagerDTO> selectManagerByNameOrAccount(@Param("query_str") String queryStr);

    /**
     * 权限列表
     * @return 所有权限
     */
    List<Permission> selectAllPermission();

    /**
     * 根据 id 查询管理员
     * @param id 管理员 id
     * @return 管理员
     */
    Manager selectManagerById(@Param("id") Integer id);

    /**
     * 根据 name 查询管理员
     * @param name 管理员 name
     * @return 管理员
     */
    Manager selectManagerByName(@Param("name") String name);

    /**
     * 根据管理员 id 查询管理员权限
     * @param id 管理员 id
     * @return 权限
     */
    List<ManagerPermission> selectPermissionByManagerId(@Param("id") Integer id);

    /**
     * 新增管理员
     * @param manager 管理员
     * @return Integer
     */
    Integer insertManager(Manager manager);

    /**
     * 删除管理员
     * @param id 标识
     */
    void deleteManager(@Param("id") Integer id);

    /**
     * 删除管理员权限
     * @param id 标识
     */
    void deleteManagerPermission(@Param("id") Integer id);

    /**
     * 修改管理员
     * @param manager 管理员
     */
    void updateManager(Manager manager);

    /**
     * 添加管理员权限
     * @param managerPermission 管理员权限表
     */
    void insertPermission(ManagerPermission managerPermission);
}
