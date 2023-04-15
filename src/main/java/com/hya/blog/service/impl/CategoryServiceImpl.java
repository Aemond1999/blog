package com.hya.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hya.blog.constant.Constant;
import com.hya.blog.enums.HttpCodeEnum;
import com.hya.blog.mapper.CategoryMapper;
import com.hya.blog.pojo.Article;
import com.hya.blog.pojo.Category;
import com.hya.blog.service.ArticleService;
import com.hya.blog.service.CategoryService;
import com.hya.blog.utils.CopyBeanUtil;
import com.hya.blog.utils.Result;
import com.hya.blog.vo.CategoryListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ArticleService articleService;
    @Override
    public Result categoryList() {
        LambdaQueryWrapper<Article> alqw=new LambdaQueryWrapper<>();
        alqw.eq(Article::getStatus, Constant.ARTICLE_STATUS_NORMAL);
        List<Article> articles = articleService.list(alqw);
        //获取所有目录ID
        Set<Long> collectIds = articles.stream().map(Article::getCategoryId).collect(Collectors.toSet());
        LambdaQueryWrapper<Category> clqw=new LambdaQueryWrapper<>();
        clqw.eq(Category::getStatus,Constant.CATEGORY_STATUS_NORMAL);
        //获取所有目录
        List<Category> categories = categoryService.listByIds(collectIds);
        //转换成VO
        List<CategoryListVO> categoryListVOs = CopyBeanUtil.copyBeanList(categories, CategoryListVO.class);
        return new Result(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg(), categoryListVOs);
    }
    //返回所有目录名称
}
