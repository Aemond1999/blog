package com.hya.common.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@TableName("blog_user")
public class UserDo {
    private Long id;

    private String account;

    private Integer admin;

    private String avatar;

    private LocalDateTime createDate;

    private Integer deleted;

    private String email;

    private LocalDateTime lastLogin;

    private String mobilePhoneNumber;

    private String nickname;

    private String password;

    private String salt;

    private String status;
}
