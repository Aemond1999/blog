package com.hya.mapper;


import com.hya.common.domain.ArticleBodyDo;
import com.hya.common.vo.ArticleBodyVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ArticleBodyMapper {
    @Select("select * from blog_article_body where id =#{id}")
    ArticleBodyVo getArticleBodyById(@Param("id") Long id);
    @Insert("insert into blog_article_body(content, content_html,article_id)  value (#{content},#{contentHtml},#{articleId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Boolean addArticleBody(ArticleBodyDo articleBodyDo);

}
