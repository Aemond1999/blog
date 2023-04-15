package com.hya.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hya.blog.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}
