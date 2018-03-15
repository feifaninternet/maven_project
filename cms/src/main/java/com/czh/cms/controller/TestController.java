package com.czh.cms.controller;

import com.czh.cms.service.TestService;
import com.czh.core.annotation.BodyParamValid;
import com.czh.core.annotation.ParameterValid;
import com.czh.core.annotation.PathAndQueryParamValid;
import com.czh.model.mini.vo.SignInVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author xfan
 * @date Created on 2018/3/14 -- 14:58
 * @desc
 */
@Api("测试")
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private TestService testService;

    @BodyParamValid
    @PostMapping("/login")
    @ApiOperation(value = "登录", httpMethod = "POST")
    public String login(@RequestBody @Valid SignInVO body, BindingResult result) {
        return testService.login(body);
    }

    @PathAndQueryParamValid
    @GetMapping("/path_str/{uuid}")
    @ApiOperation(value = "登录2",httpMethod = "GET")
    public String getMessage(@PathVariable @ParameterValid(type = String.class,msg = "uuid 不能为空") String uuid) {
        return testService.getMessage(uuid);
    }
}
