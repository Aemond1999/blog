package com.hya.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hya.blog.domain.pojo.User;
import com.hya.blog.utils.Result;
import com.hya.blog.domain.pojo.UserInToken;

public interface UserService extends IService<User> {
    Result login(User user);
    Result register(User user);
    UserInToken getUserById(Long id);

    Result logout();

}
