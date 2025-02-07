package com.hya.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hya.common.domain.TagDo;
import com.hya.common.vo.TagVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface TagMapper extends BaseMapper<TagDo> {
    List<TagVo> getTagsById(@Param("articleId") Long id);

    @Select("select * from blog_tag ")
    List<TagVo> getTags();


}
