package com.hya.blog.controller;

import com.hya.blog.common.domain.UserDO;
import com.hya.blog.service.PicUploadService;
import com.hya.blog.service.UserService;
import com.hya.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    PicUploadService picUploadService;

    @PostMapping("/login")
    Result login(@RequestBody UserDO userDO) {
        return userService.login(userDO);
    }

    @PostMapping("/register")
    Result register(@RequestBody UserDO userDO) {
        return userService.register(userDO);
    }

    @PostMapping("/upload")
    Result upload(MultipartFile img) {
        return picUploadService.upload(img);
    }
    @GetMapping("/userInfo/{id}")
    Result showUserInfo(@PathVariable Long id){
        return userService.getUserInfoById(id);
    }


}
