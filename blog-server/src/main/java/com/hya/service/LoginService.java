package com.hya.service;

import com.hya.common.dto.LoginParamDto;
import com.hya.common.dto.RegisterParamDto;
import com.hya.utils.Result;

public interface LoginService {
    Result login(LoginParamDto loginParam);
    Result logout(String token);
    Result register(RegisterParamDto registerParam);
}
