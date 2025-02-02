package com.hya.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PageParamDTO {
    // 页号
    private Long page;
    // 页面大小
    private Long pageSize;
}
