package com.hya.common.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ArticleTagDo {
    private Long id;

    private Long articleId;

    private Long tagId;
}
