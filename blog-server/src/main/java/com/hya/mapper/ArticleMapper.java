package com.hya.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hya.common.bo.ViewCountsBo;
import com.hya.common.domain.ArticleDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ArticleMapper extends BaseMapper<ArticleDo> {

    @Select("select * from  blog_article where id=#{id}")
    ArticleDo getArticleById(@Param("id") Long id);


    Boolean updateBathViewCountsById(List<ViewCountsBo> list);
}
