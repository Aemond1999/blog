package com.hya.blog.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
