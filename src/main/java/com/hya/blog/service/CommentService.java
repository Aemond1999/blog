package com.hya.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hya.blog.common.dto.CommentDTO;
import com.hya.blog.common.domain.CommentDO;
import com.hya.blog.utils.Result;


public interface CommentService extends IService<CommentDO> {
    Result commentList(Long current,Long size,Long id);
    Result sendOrReplyComment(CommentDTO commentDTO);




}
