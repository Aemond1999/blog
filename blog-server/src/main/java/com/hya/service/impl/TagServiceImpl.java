package com.hya.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hya.common.domain.TagDo;
import com.hya.common.vo.TagVo;
import com.hya.constants.RedisPrefixConstants;
import com.hya.constants.RedisTTLConstants;
import com.hya.mapper.TagMapper;
import com.hya.service.TagService;
import com.hya.utils.BeanCopyUtils;
import com.hya.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, TagDo> implements TagService {
    @Resource
    private TagMapper tagMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    /**
     * 根据文章ID获取文章所有Tag
     * @param id
     * @return
     */
    @Override
    public List<TagVo> getTagsById(Long id) {
        return tagMapper.getTagsById(id);
    }

    @Override
    public Result getTags() {
        String json = stringRedisTemplate.opsForValue().get(RedisPrefixConstants.TAGS_KEY_PREFIX);
        if (StringUtils.isBlank(json)){
            List<TagVo> tags = tagMapper.getTags();
            String jsonString = JSON.toJSONString(tags);
            stringRedisTemplate.opsForValue().set(RedisPrefixConstants.TAGS_KEY_PREFIX, jsonString, RedisTTLConstants.CACHE_TAGS_TTL, TimeUnit.MINUTES);
            return Result.success(tags);
        }
        return Result.success(JSON.parseArray(json, TagVo.class));
    }
}
