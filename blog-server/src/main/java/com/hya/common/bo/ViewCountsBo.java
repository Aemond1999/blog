package com.hya.common.bo;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ViewCountsBo {
    private Long id;
    private Integer viewCounts;

}
