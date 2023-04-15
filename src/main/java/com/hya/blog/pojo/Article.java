package com.hya.blog.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@TableName(value = "article")
public class Article {
    @TableId
    private Long id;
    @TableField("title")
    private String title;
    @TableField("content")
    private String content;
    @TableField("category_id")
    private Long categoryId;
    @TableField("is_top")
    private Boolean top;
    @TableField("status")
    private Boolean status;
    @TableField("deleted")
    private Boolean deleted;
    @TableField("view_count")
    private Long viewCount;
   @TableField( value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;
    @TableField("create_by")
    private String createBy;
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableField("update_by")
    private String updateBy;
}
