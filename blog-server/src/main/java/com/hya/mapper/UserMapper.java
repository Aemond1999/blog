package com.hya.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hya.common.domain.UserDo;
import com.hya.common.dto.LoginParamDto;
import com.hya.common.dto.RegisterParamDto;
import com.hya.common.vo.UserVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<UserDo> {
    @Select("select nickname from blog_user where id=#{id}")
    String getUserNicknameById(@Param("id") Long id);

    @Select("select * from blog_user where account=#{account} and password=#{password}")
    UserDo getUserByAccountAndPwd(LoginParamDto loginParamDto);

    @Select("select account,avatar,email,nickname from blog_user where id =#{id}")
    UserVo getUserMsgById(@Param("id") Long id);
    @Select("select * from blog_user where account =#{account}")
    UserDo getUserByAccount(@Param("account")String account);

    @Insert("insert  into  blog_user(account, password, nickname,avatar,  email,  mobile_phone_number) values (${account},${password},${nickname},${avatar},${ email},${mobilePhoneNumber})")
    Boolean addUser(RegisterParamDto registerParam);


}
