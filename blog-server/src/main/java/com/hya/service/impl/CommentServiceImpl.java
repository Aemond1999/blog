package com.hya.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hya.common.domain.CommentDo;
import com.hya.common.vo.CommentVo;
import com.hya.constants.RedisPrefixConstants;
import com.hya.constants.RedisTTLConstants;
import com.hya.enums.AppExceptionEnum;
import com.hya.exception.AppException;
import com.hya.mapper.CommentMapper;
import com.hya.service.CommentService;
import com.hya.service.UserService;
import com.hya.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.naming.ldap.PagedResultsControl;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public Result getCommentsByArticleId(Long articleId) {
        if (articleId == null) {
            throw  new AppException(AppExceptionEnum.MISS_NECESSARY_PARAM);
        }
        String json = stringRedisTemplate.opsForValue().get(RedisPrefixConstants.COMMENT_KEY_PREFIX+articleId);
        if (StringUtils.isBlank(json)) {
            List<CommentVo> commentParent = commentMapper.getCommentLevel1ByArticleId(articleId);
            System.out.println(commentParent);
            for (CommentVo commentVo : commentParent) {
                List<CommentVo> commentChildren = commentMapper.getCommentLevel2ByParentId(commentVo.getId());
                commentVo.setChildren(commentChildren);
            }
            stringRedisTemplate.opsForValue().set(RedisPrefixConstants.COMMENT_KEY_PREFIX + articleId,
                    JSON.toJSONString(commentParent),
                    RedisTTLConstants.CACHE_COMMENT_TTL,
                    TimeUnit.MINUTES);
            return Result.success(commentParent);
        }
        List<CommentVo> commentVos = JSON.parseArray(json, CommentVo.class);
        System.out.println(commentVos);
        return Result.success(commentVos);
    }
}
