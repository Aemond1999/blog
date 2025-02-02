package com.hya.common.vo;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ArticleVo {

    private Long id;

    private String title;

    private String summary;

    private Integer commentCounts;

    private Integer viewCounts;

    private Integer weight;

    private LocalDateTime createDate;

    private String author;
//
//    private ArticleBodyVo body;

    private List<TagVo> tags;
//
//    private List<CategoryVo> categorys;

}
