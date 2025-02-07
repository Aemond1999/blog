package com.hya.common.dto;

import com.hya.common.vo.TagVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ArticleParamDto {
    private Long id;

    private ArticleBodyParamDto body;

    private String summary;

    private List<TagVo> tags;

    private String title;
}
