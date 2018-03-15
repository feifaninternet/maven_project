package com.czh.cms.service;

import com.czh.model.mini.vo.SignInVO;
import org.springframework.http.ResponseEntity;

/**
 * @author xfan
 * @date Created on 2018/3/14 -- 15:00
 * @desc
 */
public interface TestService {

    String login(SignInVO body);

    String getMessage(String uuid);
}
