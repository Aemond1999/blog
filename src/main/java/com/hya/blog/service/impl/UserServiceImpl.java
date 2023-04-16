package com.hya.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hya.blog.constant.Constant;
import com.hya.blog.enums.HttpCodeEnum;
import com.hya.blog.mapper.UserMapper;
import com.hya.blog.pojo.MyUserDetails;
import com.hya.blog.pojo.User;
import com.hya.blog.service.UserService;
import com.hya.blog.utils.CopyBeanUtil;
import com.hya.blog.utils.JwtUtil;
import com.hya.blog.utils.RedisCache;
import com.hya.blog.utils.Result;
import com.hya.blog.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Objects;

@Transactional
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;

    //登录
    @Override
    public Result login(User user) {
        //封装Authentication对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        //调用authenticate进行认证
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authentication)) {
            throw new RuntimeException("用户名或密码错误");
        }
        //获取authentication中封装的用户信息
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        UserVO userVO = CopyBeanUtil.copyBean(userDetails.getUser(), UserVO.class);
        String json = JSON.toJSONString(userVO);
        //使用userid和name生成token
        String jwt = JwtUtil.createJWT(json);
        redisCache.setCacheObject("login:" + userVO.getUsername(), userDetails);
        HashMap<String, String> map = new HashMap<>();
        map.put("token", jwt);
        return new Result(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg(), map);
    }

    //注册用户
    @Override
    public Result register(User user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(password);
        return new Result(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg(), userService.save(user));
    }

    //注销用户
    @Override
    public Result logout() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        String userId = userDetails.getUser().getUsername();
        redisCache.deleteObject("login:" + userId);
        return new Result(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg(), true);
    }

    @Override
    public UserVO getUserById(Long id) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getStatus, Constant.USER_STATUS_NORMAL).eq(User::getId, id);
        User user = userService.getOne(lqw);
        return CopyBeanUtil.copyBean(user, UserVO.class);
    }


}
