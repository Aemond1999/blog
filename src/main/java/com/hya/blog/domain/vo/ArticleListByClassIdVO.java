package com.hya.blog.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class ArticleListByClassIdVO {
    private Long id;
    private String title;
    private Long viewCount;
    private Date createTime;

}
