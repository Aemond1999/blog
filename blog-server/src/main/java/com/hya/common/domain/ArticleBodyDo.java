package com.hya.common.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@TableName("blog_article_body")
public class ArticleBodyDo {
    private Long id;
    private String content;
    private String contentHtml;
    private Long articleId;
}
