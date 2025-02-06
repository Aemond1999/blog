package com.hya.common.vo;

import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CommentVo {

    private Long id;

    private UserVo author;

    private String content;

    private List<CommentVo> children;

    private String createDate;

    private Integer level;

    private UserVo toUser;
}
