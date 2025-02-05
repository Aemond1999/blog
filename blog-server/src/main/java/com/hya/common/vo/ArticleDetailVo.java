package com.hya.common.vo;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ArticleDetailVo {
    private Long id;
    private String title;
    private Integer commentCounts;
    private Integer viewCounts;
    private LocalDateTime createDate;
    private String author;
    private ArticleBodyVo body;
    private List<TagVo> tags;
}
