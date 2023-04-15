package com.hya.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hya.blog.pojo.Article;
import com.hya.blog.utils.Result;

import java.util.List;

public interface ArticleService extends IService<Article> {
    Result articleListByClassId(Long current,Long size,Long id);
    Result getArticleContent(Long id);

}
