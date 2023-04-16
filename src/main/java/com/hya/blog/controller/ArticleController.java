package com.hya.blog.controller;

import com.hya.blog.service.ArticleService;
import com.hya.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private  ArticleService articleService;

    @GetMapping("/test1/{current}/{size}/{id}")
    public Result test1(@PathVariable Long current, @PathVariable Long size,@PathVariable Long id) {
        return  articleService.articleListByClassId( current,size,id);

    }
    @GetMapping("/test2/{id}")
    public Result test1( @PathVariable Long id) {
        return articleService.getArticleContent(id);

    }

}
