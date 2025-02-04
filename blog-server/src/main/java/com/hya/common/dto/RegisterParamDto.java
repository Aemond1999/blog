package com.hya.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class RegisterParamDto {
    private String account;
    private String avatar;
    private String email;
    private String mobilePhoneNumber;
    private String nickname;
    private String password;
}
