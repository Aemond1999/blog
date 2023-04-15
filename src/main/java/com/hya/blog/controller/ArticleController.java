package com.hya.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hya.blog.mapper.ArticleMapper;
import com.hya.blog.pojo.Article;
import com.hya.blog.service.ArticleService;
import com.hya.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

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
