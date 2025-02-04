package com.hya.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hya.common.domain.UserDo;
import com.hya.common.dto.LoginParamDto;
import com.hya.common.dto.PageParamDTO;
import com.hya.common.dto.RegisterParamDto;
import com.hya.constants.RedisPrefixConstants;
import com.hya.constants.RedisTTLConstants;
import com.hya.enums.AppExceptionEnum;
import com.hya.exception.AppException;
import com.hya.mapper.UserMapper;
import com.hya.service.LoginService;
import com.hya.service.UserService;
import com.hya.utils.JWTUtils;
import com.hya.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private UserService userService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result login(LoginParamDto loginParam) {
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)) {
            // 账号密码不能为空
            throw new AppException(AppExceptionEnum.ACCOUNT_OR_PWD_BLANK);
        }
        UserDo user = userService.getUserByAccountAndPwd(loginParam);
        if (user == null) {
            // 用户不存在
            throw new AppException(AppExceptionEnum.ACCOUNT_OR_PWD_ERROR);
        }
        // 生成token
        String token = JWTUtils.createToken(user.getId());
        stringRedisTemplate
                .opsForValue()
                .set(RedisPrefixConstants.TOKEN_KEY_PREFIX + token,
                        JSON.toJSONString(user),
                        RedisTTLConstants.TOKEN_EXPIRE_TTL,
                        TimeUnit.MILLISECONDS);
        return Result.success(token);
    }

    @Override
    public Result logout(String token) {
        if (StringUtils.isBlank(token)) {
            throw new AppException(AppExceptionEnum.INVALID_TOKEN);
        }
        Boolean flag= stringRedisTemplate.delete(RedisPrefixConstants.TOKEN_KEY_PREFIX + token);
        if (flag) {
            return Result.success(null);
        }
        else {
            throw new AppException(AppExceptionEnum.LOGIN_EXPIRED);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result register(RegisterParamDto registerParam) {
        if (registerParam == null) {
            throw new AppException(AppExceptionEnum.ACCOUNT_OR_PWD_BLANK);
        }
        if (StringUtils.isBlank(registerParam.getAccount()) || StringUtils.isBlank(registerParam.getPassword())) {
            throw new AppException(AppExceptionEnum.ACCOUNT_OR_PWD_BLANK);
        }
        if (userService.getUserByAccount(registerParam.getAccount()) != null) {
            throw new AppException(AppExceptionEnum.USER_EXISTED);
        }
        Boolean flag = userService.addUser(registerParam);
        if (!flag) {
            throw new AppException(AppExceptionEnum.REGISTER_FAIL);
        }
        return Result.success(null);
    }

}

