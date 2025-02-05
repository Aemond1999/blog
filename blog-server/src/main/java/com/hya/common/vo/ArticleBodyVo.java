package com.hya.common.vo;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ArticleBodyVo {
    private Long id;
    private String content;
    private String contentHtml;
}
