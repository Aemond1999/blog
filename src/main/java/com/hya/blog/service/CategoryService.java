package com.hya.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hya.blog.common.domain.CategoryDO;
import com.hya.blog.utils.Result;

public interface CategoryService extends IService<CategoryDO> {
    Result categoryList();

}
