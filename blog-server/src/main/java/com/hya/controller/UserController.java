package com.hya.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hya.enums.AppExceptionEnum;
import com.hya.exception.AppException;
import com.hya.service.UserService;
import com.hya.utils.JWTUtils;
import com.hya.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 获取当前用户信息
     *
     * @return
     */
    @GetMapping("current")
    public Result current(@RequestHeader("Authorization") String token) {
        return userService.getUserMsgByToken(token);
    }
}
