package com.czh.orm.mybatis.mini;

import com.czh.model.cms.entity.Manager;
import com.czh.model.common.Token;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author xfan
 * @date Created on 2018/2/7 -- 15:24
 * @desc
 */
@Repository
public interface TokenMapper {

    /**
     * 为用户添加 token 密钥
     * @param token 密钥
     */
    void insertToken(Token token);

    /**
     * 根据 uuid 查询用户 token
     * @param uuid uuid
     * @return string
     */
    Token selectTokenByUuid(@Param("uuid") String uuid);

    /**
     * 根据 token 查询用户 uuid
     * @param token token
     * @return string
     */
    String selectUuidByToken(@Param("token") String token);

    /**
     * 根据 token 查询管理员
     * @param token 管理员token
     * @return 管理员 id
     */
    Manager selectIdByToken(@Param("token") String token);

    /**
     * 更新管理员 token
     * @param map map
     */
    void updateManagerToken(Map<String, Object> map);

    /**
     * 更新用户 token
     * @param map map
     */
    void updateUserToken(Map<String, Object> map);
}
