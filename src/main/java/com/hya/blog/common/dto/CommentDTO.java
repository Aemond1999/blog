package com.hya.blog.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class CommentDTO {
    private Long id;
    private String content;
    private Date createTime;
    private Long createBy;
    private Long toCommentUserId;
    private String toCommentUserName;
    private Long toCommentId;
    private String UserName;
}
