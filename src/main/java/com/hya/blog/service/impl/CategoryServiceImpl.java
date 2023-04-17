package com.hya.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hya.blog.constant.Constant;
import com.hya.blog.common.domain.ArticleDO;
import com.hya.blog.enums.HttpCodeEnum;
import com.hya.blog.mapper.CategoryMapper;
import com.hya.blog.common.domain.CategoryDO;
import com.hya.blog.service.ArticleService;
import com.hya.blog.service.CategoryService;
import com.hya.blog.utils.CopyBeanUtil;
import com.hya.blog.utils.Result;
import com.hya.blog.common.vo.CategoryListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryDO> implements CategoryService {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ArticleService articleService;
    //返回目录ID和名称
    @Override
    public Result categoryList() {
        LambdaQueryWrapper<ArticleDO> alqw=new LambdaQueryWrapper<>();
        alqw.eq(ArticleDO::getStatus, Constant.ARTICLE_STATUS_NORMAL);
        List<ArticleDO> articleDOS = articleService.list(alqw);
        //获取所有目录ID
        Set<Long> collectIds = articleDOS.stream().map(ArticleDO::getCategoryId).collect(Collectors.toSet());
        LambdaQueryWrapper<CategoryDO> clqw=new LambdaQueryWrapper<>();
        clqw.eq(CategoryDO::getStatus,Constant.CATEGORY_STATUS_NORMAL);
        //获取所有目录
        List<CategoryDO> categories = categoryService.listByIds(collectIds);
        //转换成VO
        List<CategoryListVO> categoryListVOs = CopyBeanUtil.copyBeanList(categories, CategoryListVO.class);
        return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg(), categoryListVOs);
    }
    //返回所有目录名称
}
