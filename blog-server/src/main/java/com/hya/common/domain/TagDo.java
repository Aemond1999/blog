package com.hya.common.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@TableName("blog_tag")
public class TagDo {
    private Long id;

    private String avatar;

    private String tagName;
}
