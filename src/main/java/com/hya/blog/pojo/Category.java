package com.hya.blog.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@TableName(value = "category")
public class Category {
    @TableId
    private Long id;
    @TableField("pid")
    private Long pid;
    @TableField("category_name")
    private String categoryName;
    @TableField("status")
    private Boolean status;
    @TableField("deleted")
    private Boolean deleted;
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;
    @TableField("create_by")
    private Long createBy;
    @TableField(value = "update_time",fill=FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableField("update_by")
    private String updateBy;
}
