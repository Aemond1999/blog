package com.hya.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hya.blog.common.dto.UserInfoDTO;
import com.hya.blog.constant.Constant;
import com.hya.blog.enums.HttpCodeEnum;
import com.hya.blog.mapper.UserMapper;
import com.hya.blog.common.pojo.MyUserDetails;
import com.hya.blog.common.domain.UserDO;
import com.hya.blog.service.UserService;
import com.hya.blog.utils.CopyBeanUtil;
import com.hya.blog.utils.JwtUtil;
import com.hya.blog.utils.RedisCache;
import com.hya.blog.utils.Result;
import com.hya.blog.common.bo.UserInTokenBO;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Objects;

@Transactional
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {
    @Autowired
    UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    UserMapper userMapper;

    //登录
    @Override
    public Result login(UserDO userDO) {
        //封装Authentication对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDO.getUsername(), userDO.getPassword());
        //调用authenticate进行认证
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authentication)) {
            throw new RuntimeException("用户名或密码错误");
        }
        //获取authentication中封装的用户信息
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        UserInTokenBO userInTokenBO = CopyBeanUtil.copyBean(userDetails.getUserDO(), UserInTokenBO.class);
        String json = JSON.toJSONString(userInTokenBO);
        //使用userid和name生成token
        String jwt = JwtUtil.createJWT(json);
        redisCache.setCacheObject("login:" + userInTokenBO.getUsername(), userDetails);
        HashMap<String, String> map = new HashMap<>();
        map.put("token", jwt);
        return new Result(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg(), map);
    }

    //注册用户
    @Override
    public Result register(@NotNull UserDO userDO) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = bCryptPasswordEncoder.encode(userDO.getPassword());
        userDO.setPassword(password);
        return new Result(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg(), userService.save(userDO));
    }

    //注销用户
    @Override
    public Result logout() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        String userId = userDetails.getUserDO().getUsername();
        redisCache.deleteObject("login:" + userId);
        return new Result(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg(), true);
    }

    //展示用户个人信息
    @Override
    public Result getUserInfoById(Long id) {
        UserDO userDO= userService.getById(id);
        UserInfoDTO userInfoDTO = CopyBeanUtil.copyBean(userDO, UserInfoDTO.class);
        return new Result(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg(), userInfoDTO);
    }

    @Override
    public Result updateUserInfo(UserInfoDTO userInfoDTO) {
        boolean flag = userService.updateById(CopyBeanUtil.copyBean(userInfoDTO, UserDO.class));
        return new Result(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg(), flag);
    }

    @Override
    public Boolean updateAvatar(String avatar, Long id) {
        return userMapper.updateAvatar(avatar,id);
    }


    //获取用户名和ID
    @Override
    public UserInTokenBO getUserById(Long id) {
        LambdaQueryWrapper<UserDO> lqw = new LambdaQueryWrapper<>();
        lqw.eq(UserDO::getStatus, Constant.USER_STATUS_NORMAL).eq(UserDO::getId, id);
        UserDO userDO = userService.getOne(lqw);
        return CopyBeanUtil.copyBean(userDO, UserInTokenBO.class);
    }


}
