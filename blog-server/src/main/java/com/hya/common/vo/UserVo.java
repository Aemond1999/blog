package com.hya.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
/**
 * 用户个人数据
 */
public class UserVo {
    private String account;
    private String avatar;
    private String email;
    private String nickname;
}
