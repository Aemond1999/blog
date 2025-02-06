package com.hya.common.vo;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class UserVo {
    private Long id;
    private String avatar;
    private String nickname;
}
