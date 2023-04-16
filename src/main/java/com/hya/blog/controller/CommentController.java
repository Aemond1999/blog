package com.hya.blog.controller;

import com.hya.blog.domain.dto.CommentDTO;
import com.hya.blog.service.CommentService;
import com.hya.blog.utils.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("/test1/{current}/{size}/{id}")
    public  Result test1(@PathVariable Long current, @PathVariable Long size,@PathVariable Long id){
        return commentService.commentList(current,size,id);
    }
    @PostMapping("/test2")
    public  Result test2(@RequestBody CommentDTO commentDTO){
        return commentService.sendOrReplyComment(commentDTO);
    }

}
