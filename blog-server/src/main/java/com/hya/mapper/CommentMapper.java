package com.hya.mapper;

import com.hya.common.domain.CommentDo;
import com.hya.common.vo.CommentVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {


    List<CommentVo> getCommentLevel1ByArticleId(@Param("articleId") Long articleId);

    List<CommentVo> getCommentLevel2ByParentId(@Param("parentId") Long parentId);
}
