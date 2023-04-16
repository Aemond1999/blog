package com.hya.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hya.blog.domain.pojo.Article;
import com.hya.blog.utils.Result;

public interface ArticleService extends IService<Article> {
    Result articleListByClassId(Long current,Long size,Long id);
    Result getArticleContent(Long id);

}
