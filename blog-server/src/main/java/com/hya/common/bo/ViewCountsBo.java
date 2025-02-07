package com.hya.common.bo;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
/**
 * 用于更新评论数
 */
public class ViewCountsBo {
    private Long id;
    private Integer viewCounts;

}
