package com.hya.blog.common.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class ArticleContentVO {
    private Long id;
    private String title;
    private String content;
    private Long viewCount;
    private Date createTime;

}
