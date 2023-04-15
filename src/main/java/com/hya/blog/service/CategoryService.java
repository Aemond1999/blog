package com.hya.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hya.blog.pojo.Category;
import com.hya.blog.utils.Result;

import java.util.List;

public interface CategoryService extends IService<Category> {
    Result categoryList();

}
