package com.hya.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hya.common.domain.UserDo;
import com.hya.common.dto.LoginParamDto;
import com.hya.common.dto.RegisterParamDto;
import com.hya.common.vo.UserVo;
import com.hya.utils.Result;
import org.apache.ibatis.annotations.Param;

public interface UserService extends IService<UserDo> {
    String getUserNicknameById(@Param("id") Long id);
    UserDo getUserByAccountAndPwd(LoginParamDto loginParamDto);
    Result getUserMsgByToken(String token);
   Boolean addUser(RegisterParamDto registerParam);
    UserDo getUserByAccount(String account);
}
