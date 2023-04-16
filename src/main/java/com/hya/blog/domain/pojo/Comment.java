package com.hya.blog.domain.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Comment {
    @TableId
    private Long id;
    @TableField("article_id")
    private Long articleId;
    @TableField("root_id")
    private Long rootId;
    @TableField("content")
    private String content;
    @TableField("deleted")
    private Boolean deleted;
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField( value = "create_by",fill = FieldFill.INSERT)
    private Long createBy;
    @TableField(value = "update_time",fill=FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField(value = "update_by",fill=FieldFill.INSERT_UPDATE)
    private Long updateBy;
    @TableField("to_comment_user_id")
    private Long toCommentUserId;
    @TableField("to_comment_id")
    private Long toCommentId;

}
