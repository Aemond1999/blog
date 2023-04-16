package com.hya.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hya.blog.pojo.User;
import com.hya.blog.utils.RedisCache;
import com.hya.blog.utils.Result;
import com.hya.blog.vo.UserVO;

public interface UserService extends IService<User> {
    Result login(User user);
    Result register(User user);
    UserVO getUserById(Long id);

    Result logout();

}
