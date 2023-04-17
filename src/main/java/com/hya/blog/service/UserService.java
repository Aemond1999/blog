package com.hya.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hya.blog.common.domain.UserDO;
import com.hya.blog.common.dto.UserInfoDTO;
import com.hya.blog.utils.Result;
import com.hya.blog.common.bo.UserInTokenBO;
import org.springframework.web.multipart.MultipartFile;

public interface UserService extends IService<UserDO> {
    Result login(UserDO userDO);
    Result register(UserDO userDO);
    UserInTokenBO getUserById(Long id);
    Result logout();
    Result getUserInfoById(Long id);
    Result updateUserInfo(UserInfoDTO userInfoDTO);
    Boolean updateAvatar(String avatar,Long id);




}
