package com.hya.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hya.common.domain.TagDo;
import com.hya.common.domain.UserDo;
import com.hya.common.dto.LoginParamDto;
import com.hya.common.dto.RegisterParamDto;
import com.hya.constants.RedisPrefixConstants;
import com.hya.enums.AppExceptionEnum;
import com.hya.exception.AppException;
import com.hya.mapper.TagMapper;
import com.hya.mapper.UserMapper;
import com.hya.service.UserService;
import com.hya.utils.JWTUtils;
import com.hya.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDo> implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public String getUserNicknameById(Long id) {
        return userMapper.getUserNicknameById(id);
    }

    @Override
    public UserDo getUserByAccountAndPwd(LoginParamDto loginParamDto) {
        return userMapper.getUserByAccountAndPwd(loginParamDto);
    }

    @Override
    public Result getUserMsgByToken(String token) {
        if (StringUtils.isBlank(token)) {
            throw new AppException(AppExceptionEnum.INVALID_TOKEN);
        }
        String json = stringRedisTemplate.opsForValue().get((RedisPrefixConstants.TOKEN_KEY_PREFIX + token));
        if (StringUtils.isBlank(json)) {
            throw new AppException(AppExceptionEnum.NOT_LOGIN);
        }
        return Result.success(userMapper.getUserMsgById(JSON.parseObject(json, UserDo.class).getId()));
    }

   
    @Override
    public Boolean addUser(RegisterParamDto registerParam) {
        return userMapper.addUser(registerParam);
    }


    @Override
    public UserDo getUserByAccount(String account) {
        return userMapper.getUserByAccount(account);
    }

}
