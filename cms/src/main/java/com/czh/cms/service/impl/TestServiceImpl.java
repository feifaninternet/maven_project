package com.czh.cms.service.impl;

import com.czh.cms.service.TestService;
import com.czh.model.mini.vo.SignInVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author xfan
 * @date Created on 2018/3/14 -- 15:01
 * @desc
 */
@Service
public class TestServiceImpl implements TestService {

    @Override
    public String login(SignInVO body){
        return "aaa";
    }

    @Override
    public String getMessage(String uuid) {
        return "aaa";
    }
}
