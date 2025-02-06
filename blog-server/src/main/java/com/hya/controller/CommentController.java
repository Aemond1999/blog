package com.hya.controller;

import com.hya.service.CommentService;
import com.hya.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("comment")
public class CommentController {
    @Resource
    private CommentService commentService;

    @GetMapping("list/{articleId}")
    public Result list(@PathVariable("articleId") Long articleId) {
     return  commentService.getCommentsByArticleId(articleId);
    }
}
