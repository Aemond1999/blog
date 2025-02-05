package com.hya.controller;

import com.hya.common.dto.PageParamDTO;
import com.hya.service.ArticleService;
import com.hya.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("article")
public class ArticleController {
    @Resource
    private ArticleService articleService;

    /**
     * 获取所有文章
     * @param pageParam
     * @return
     */
    @GetMapping("list")
    Result list(@RequestBody PageParamDTO pageParam) {
        return articleService.listArticle(pageParam);
    }
    @GetMapping("detail/{id}")
    Result detail(@PathVariable("id") Long id) {
        return articleService.getArticleDetail(id);
    }




}
