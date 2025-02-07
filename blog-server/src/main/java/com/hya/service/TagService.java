package com.hya.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hya.common.domain.TagDo;
import com.hya.common.vo.TagVo;
import com.hya.utils.Result;

import java.util.List;

public interface TagService  extends IService<TagDo> {
    List<TagVo> getTagsById(Long id);
    Result getTags();
}
