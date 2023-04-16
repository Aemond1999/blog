package com.hya.blog.controller;

import com.hya.blog.common.domain.UserDO;
import com.hya.blog.service.UserService;
import com.hya.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/login")
    Result login(@RequestBody UserDO userDO){
      return   userService.login(userDO);
    }

    @PostMapping("/register")
    Result register(@RequestBody UserDO userDO){
        return userService.register(userDO);
    }



}
