package com.hya.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hya.common.domain.TagDo;
import com.hya.common.domain.UserDo;
import com.hya.mapper.TagMapper;
import com.hya.mapper.UserMapper;
import com.hya.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDo> implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public String getUserNicknameById(Long id) {
        return userMapper.getUserNicknameById(id);
    }
}
