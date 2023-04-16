package com.hya.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hya.blog.constant.Constant;
import com.hya.blog.common.domain.ArticleDO;
import com.hya.blog.enums.HttpCodeEnum;
import com.hya.blog.mapper.ArticleMapper;
import com.hya.blog.service.ArticleService;
import com.hya.blog.utils.CopyBeanUtil;
import com.hya.blog.utils.Result;
import com.hya.blog.common.vo.ArticleContentVO;
import com.hya.blog.common.vo.ArticleListByClassIdVO;
import com.hya.blog.common.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, ArticleDO> implements ArticleService {
    @Autowired
    private ArticleService articleService;
    //根据目录iD获取文章
    @Override
    public Result articleListByClassId(Long current, Long size, Long id) {
        IPage<ArticleDO> pageAll=new Page<>(current,size);
        LambdaQueryWrapper<ArticleDO> lqw=new LambdaQueryWrapper<>();
        lqw
                .eq(ArticleDO::getStatus, Constant.ARTICLE_STATUS_NORMAL)
                .eq(ArticleDO::getCategoryId,id)
                .orderByDesc(ArticleDO::getTop);

        IPage<ArticleDO> pageNow = articleService.page(pageAll, lqw);
        List<ArticleListByClassIdVO> articleListByClassIdVOs = CopyBeanUtil.copyBeanList(pageNow.getRecords(), ArticleListByClassIdVO.class);
        PageVO pageVO=new PageVO(articleListByClassIdVOs,pageNow.getTotal(),pageNow.getCurrent(),pageNow.getSize());
        return new Result(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg(), pageVO);
    }
//根据文章ID获取文章内容
    @Override
    public Result getArticleContent(Long id) {
        ArticleDO articleDO = articleService.getById(id);
        return new Result(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg(), CopyBeanUtil.copyBean(articleDO, ArticleContentVO.class));
    }
}
