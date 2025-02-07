package com.hya.controller;

import com.hya.common.dto.ArticleParamDto;
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
     *
     * @param pageParam
     * @return
     */
    @GetMapping("list")
    Result list(@RequestBody PageParamDTO pageParam) {
        return articleService.listArticle(pageParam);
    }

    /**
     * 获取文章详情
     *
     * @param id
     * @return
     */
    @GetMapping("detail/{id}")
    Result detail(@PathVariable("id") Long id) {
        return articleService.getArticleDetail(id);
    }

    /**
     * 添加文章
     * @param articleParamDto
     * @return
     */
    @PutMapping("publish")
    public synchronized Result publish(@RequestBody ArticleParamDto articleParamDto){


           return articleService.publishArticle(articleParamDto);

    }


}
