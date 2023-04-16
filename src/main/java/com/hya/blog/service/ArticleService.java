package com.hya.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hya.blog.common.domain.ArticleDO;
import com.hya.blog.utils.Result;

public interface ArticleService extends IService<ArticleDO> {
    Result articleListByClassId(Long current,Long size,Long id);
    Result getArticleContent(Long id);

}
