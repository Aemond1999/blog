package com.hya.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hya.blog.domain.pojo.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}
