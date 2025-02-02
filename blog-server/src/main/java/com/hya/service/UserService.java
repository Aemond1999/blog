package com.hya.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hya.common.domain.UserDo;
import org.apache.ibatis.annotations.Param;

public interface UserService extends IService<UserDo> {
    public String getUserNicknameById(@Param("id") Long id);
}
