package com.hya.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hya.common.domain.ArticleDo;
import com.hya.common.dto.PageParamDTO;
import com.hya.utils.Result;

public interface ArticleService extends IService<ArticleDo> {
    Result listArticle(PageParamDTO pageParam);
}
