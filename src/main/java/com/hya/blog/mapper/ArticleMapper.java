package com.hya.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hya.blog.pojo.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

}
