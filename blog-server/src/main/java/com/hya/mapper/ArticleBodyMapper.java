package com.hya.mapper;


import com.hya.common.vo.ArticleBodyVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ArticleBodyMapper {
    @Select("select * from blog_article_body where id =#{id}")
    ArticleBodyVo getArticleBodyById(@Param("id") Long id);

}
