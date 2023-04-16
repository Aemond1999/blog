package com.hya.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hya.blog.constant.Constant;
import com.hya.blog.mapper.UserMapper;
import com.hya.blog.pojo.User;
import com.hya.blog.service.UserService;
import com.hya.blog.utils.CopyBeanUtil;
import com.hya.blog.utils.Result;
import com.hya.blog.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    UserService userService;

    @Override
    public UserVO getUserById(Long id) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getStatus,Constant.USER_STATUS_NORMAL).eq(User::getId,id);
        User user = userService.getOne(lqw);
        return CopyBeanUtil.copyBean(user, UserVO.class);
    }


}
