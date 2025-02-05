package com.hya.service;

import com.hya.common.domain.ArticleBodyDo;
import com.hya.common.vo.ArticleBodyVo;
import com.hya.utils.Result;
import org.apache.ibatis.annotations.Param;

public interface ArticleBodyService {
    ArticleBodyVo getArticleBodyById(Long id);
}
