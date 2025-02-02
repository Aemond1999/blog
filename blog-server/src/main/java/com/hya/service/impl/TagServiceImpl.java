package com.hya.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hya.common.domain.TagDo;
import com.hya.common.vo.TagVo;
import com.hya.mapper.TagMapper;
import com.hya.service.TagService;
import com.hya.utils.BeanCopyUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, TagDo> implements TagService {
    @Resource
    private TagMapper tagMapper;
    /**
     * 根据文章ID获取文章所有Tag
     * @param id
     * @return
     */
    @Override
    public List<TagVo> getTagsById(Long id) {
        return  BeanCopyUtils.copyBeanList(tagMapper.getTagsById(id), TagVo.class);
    }
}
