package com.hya.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hya.common.domain.ArticleDo;
import com.hya.common.dto.PageParamDTO;
import com.hya.common.vo.ArticleVo;
import com.hya.mapper.ArticleMapper;
import com.hya.service.ArticleService;
import com.hya.service.TagService;
import com.hya.service.UserService;
import com.hya.utils.BeanCopyUtils;
import com.hya.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, ArticleDo> implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private TagService tagService;
    @Resource
    private UserService userService;

    /**
     * 获取所有文章
     *
     * @param pageParam
     * @return
     */
    public Result listArticle(PageParamDTO pageParam) {

        Page<ArticleDo> page = new Page<>(pageParam.getPage(), pageParam.getPageSize());
        LambdaQueryWrapper<ArticleDo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(ArticleDo::getWeight);
        Page<ArticleDo> records = articleMapper.selectPage(page,queryWrapper);
        List<ArticleVo> articleVos = copyList(records.getRecords());
        return Result.success(articleVos);
    }
    private List<ArticleVo> copyList(List<ArticleDo> records) {
        List<ArticleVo> articles = new ArrayList<>();
        for (ArticleDo record : records) {
            ArticleVo articleVo = BeanCopyUtils.copyBean(record, ArticleVo.class);
            articleVo.setAuthor(userService.getUserNicknameById(record.getAuthorId()));
            articleVo.setTags(tagService.getTagsById(record.getId()));
            articles.add(articleVo);
        }
        return articles;
    }
}
