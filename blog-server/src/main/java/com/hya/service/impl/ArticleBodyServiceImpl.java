package com.hya.service.impl;

import com.hya.common.vo.ArticleBodyVo;
import com.hya.mapper.ArticleBodyMapper;
import com.hya.service.ArticleBodyService;
import com.hya.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ArticleBodyServiceImpl implements ArticleBodyService {
    @Resource
    private ArticleBodyMapper articleBodyMapper;

    @Override
    public ArticleBodyVo getArticleBodyById(Long id) {
        return articleBodyMapper.getArticleBodyById( id);
    }
}
