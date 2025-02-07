package com.hya.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hya.common.bo.ViewCountsBo;
import com.hya.common.domain.ArticleBodyDo;
import com.hya.common.domain.ArticleDo;
import com.hya.common.domain.ArticleTagDo;
import com.hya.common.dto.ArticleBodyParamDto;
import com.hya.common.vo.TagVo;
import org.apache.ibatis.annotations.*;


import java.util.List;

@Mapper
public interface ArticleMapper extends BaseMapper<ArticleDo> {

    @Select("select * from  blog_article where id=#{id}")
    ArticleDo getArticleById(@Param("id") Long id);

    Boolean updateBathViewCountsById(List<ViewCountsBo> list);


    @Insert("insert into blog_article(title,summary,author_id) value (#{title},#{summary},#{authorId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Boolean addArticle(ArticleDo articleDo);

    Boolean addArticleTags(List<ArticleTagDo> tags);

    @Update("update blog_article set body_id=#{bodyId} where id=#{id}")
    Boolean updateArticleBodyIdById(@Param("bodyId") Long bodyId, @Param("id") Long id);


}
