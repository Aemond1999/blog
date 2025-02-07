package com.hya.controller;

import com.hya.service.TagService;
import com.hya.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tag")
public class TagController {
    @Resource private TagService tagService;
    @RequestMapping("list")
    public  Result list(){
        return tagService.getTags();
    }
}
