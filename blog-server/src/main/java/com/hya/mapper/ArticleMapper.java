package com.hya.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hya.common.domain.ArticleDo;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface ArticleMapper extends BaseMapper<ArticleDo> {
}
