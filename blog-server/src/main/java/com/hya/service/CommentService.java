package com.hya.service;

import com.hya.common.vo.CommentVo;
import com.hya.utils.Result;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentService {
  Result getCommentsByArticleId(Long articleId);

}
