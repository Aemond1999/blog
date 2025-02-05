package com.hya.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hya.common.domain.ArticleDo;
import com.hya.common.dto.PageParamDTO;
import com.hya.common.vo.ArticleBodyVo;
import com.hya.common.vo.ArticleDetailVo;
import com.hya.common.vo.ArticleVo;
import com.hya.constants.RedisPrefixConstants;
import com.hya.constants.RedisTTLConstants;
import com.hya.enums.AppExceptionEnum;
import com.hya.exception.AppException;
import com.hya.mapper.ArticleMapper;
import com.hya.service.ArticleBodyService;
import com.hya.service.ArticleService;
import com.hya.service.TagService;
import com.hya.service.UserService;
import com.hya.utils.BeanCopyUtils;
import com.hya.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, ArticleDo> implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private TagService tagService;
    @Resource
    private UserService userService;
    @Resource
    private ArticleBodyService articleBodyService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

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
        Page<ArticleDo> records = articleMapper.selectPage(page, queryWrapper);

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

    @Override
    public Result getArticleDetail(Long id) {
        String json = stringRedisTemplate.opsForValue().get(RedisPrefixConstants.ARTICLE_DETAIL_KEY_PREFIX + id);
        if (StringUtils.isBlank(json)) {
            ArticleDo article = articleMapper.getArticleById(id);
            if (article == null) {
                throw new AppException(AppExceptionEnum.ARTICLE_NOT_EXISTS);
            }
            Long bodyId = article.getBodyId();
            // 查询文章主体
            ArticleDetailVo articleDetailVo = copy(article);
            // 添加缓存
            stringRedisTemplate.opsForValue().set(RedisPrefixConstants.ARTICLE_DETAIL_KEY_PREFIX + id,
                    JSON.toJSONString(articleDetailVo),
                    RedisTTLConstants.CACHE_ARTICLE_DETAIL_TTL, TimeUnit.MINUTES);
            return Result.success(articleDetailVo);
        }
        return Result.success(JSON.parseObject(json, ArticleDetailVo.class));
    }
    private ArticleDetailVo copy(ArticleDo article) {
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        articleDetailVo.setBody(articleBodyService.getArticleBodyById(article.getBodyId()));
        return articleDetailVo;
    }


}
