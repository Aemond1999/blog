package com.hya.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hya.common.domain.TagDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface TagMapper extends BaseMapper<TagDo> {

    List<TagDo> getTagsById(@Param("articleId") Long id);
}
