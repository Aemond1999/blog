package com.hya.blog.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class CommentListVO {
    private Long id;
    private String content;
    private Date createTime;
    private Long createBy;
    private Long toCommentUserId;
    private String toCommentUserName;
    private Long toCommentId;
    private String UserName;
    private List<CommentListVO> children;
}
