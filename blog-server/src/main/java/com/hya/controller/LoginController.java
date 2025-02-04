package com.hya.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hya.common.dto.LoginParamDto;
import com.hya.common.dto.RegisterParamDto;
import com.hya.constants.RedisPrefixConstants;
import com.hya.enums.AppExceptionEnum;
import com.hya.exception.AppException;
import com.hya.service.LoginService;
import com.hya.service.UserService;
import com.hya.utils.JWTUtils;
import com.hya.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController

public class LoginController {
    @Resource
    private LoginService loginService;

    @PostMapping("login")
    public Result login(@RequestBody LoginParamDto loginParam){
        return loginService.login(loginParam);
    }
    @PostMapping("logout")
    public Result logout(@RequestHeader("Authorization") String token){
       return loginService.logout(token);
    }
    @PutMapping("register")
    public Result register(@RequestBody RegisterParamDto registerParam){
        return loginService.register(registerParam);
    }



}
