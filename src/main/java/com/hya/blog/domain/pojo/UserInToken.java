package com.hya.blog.domain.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserInToken {
    private Long id;
    private String username;
}
