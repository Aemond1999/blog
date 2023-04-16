package com.hya.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hya.blog.domain.pojo.Category;
import com.hya.blog.utils.Result;

public interface CategoryService extends IService<Category> {
    Result categoryList();

}
