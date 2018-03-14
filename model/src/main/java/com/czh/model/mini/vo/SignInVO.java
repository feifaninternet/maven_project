package com.czh.model.mini.vo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @author xfan
 * @date Created on 2018/3/14 -- 15:03
 * @desc
 */
public class SignInVO {

    @Getter @Setter
    @NotNull(message = "Number is null")
    private Integer number;

    @Getter @Setter
    @NotBlank(message = "Name id blank")
    private String name;
}
