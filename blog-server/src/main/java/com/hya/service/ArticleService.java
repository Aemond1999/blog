package com.hya.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hya.common.domain.ArticleDo;
import com.hya.common.dto.ArticleParamDto;
import com.hya.common.dto.PageParamDTO;
import com.hya.utils.Result;
import org.apache.ibatis.annotations.Param;

public interface ArticleService extends IService<ArticleDo> {
    Result listArticle(PageParamDTO pageParam);

    Result getArticleDetail(Long id);

    Result publishArticle(ArticleParamDto articleParamDto);


}
