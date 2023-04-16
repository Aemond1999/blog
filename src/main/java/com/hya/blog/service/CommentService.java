package com.hya.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hya.blog.domain.dto.CommentDTO;
import com.hya.blog.domain.pojo.Comment;
import com.hya.blog.utils.Result;


public interface CommentService extends IService<Comment> {
    Result commentList(Long current,Long size,Long id);
    Result sendOrReplyComment(CommentDTO commentDTO);




}
