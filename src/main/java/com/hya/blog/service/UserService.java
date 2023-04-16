package com.hya.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hya.blog.common.domain.UserDO;
import com.hya.blog.utils.Result;
import com.hya.blog.common.bo.UserInTokenBO;

public interface UserService extends IService<UserDO> {
    Result login(UserDO userDO);
    Result register(UserDO userDO);
    UserInTokenBO getUserById(Long id);
    Result logout();

    Result getUserInfoById(Long id);



}
