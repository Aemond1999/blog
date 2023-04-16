package com.hya.blog.controller;

import com.hya.blog.domain.pojo.User;
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
    Result login(@RequestBody User user){
      return   userService.login(user);
    }

    @PostMapping("/register")
    Result register(@RequestBody User user){
        return userService.register(user);
    }



}
