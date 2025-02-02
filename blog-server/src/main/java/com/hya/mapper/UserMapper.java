package com.hya.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hya.common.domain.UserDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<UserDo> {
    public String getUserNicknameById(@Param("id") Long id);
}
