package com.hya.blog.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class CommentVO {
    private Long id;
    private String content;
    private Date createTime;
    private Long createBy;
    private Long toCommentUserId;
    private String toCommentUserName;
    private Long toCommentId;
    private String UserName;
    private List<CommentVO> children;
}
