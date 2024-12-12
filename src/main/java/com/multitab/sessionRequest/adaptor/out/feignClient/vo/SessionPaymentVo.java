package com.multitab.sessionRequest.adaptor.out.feignClient.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionPaymentVo {

    private String sessionUuid;
    private String menteeUuid;
    private String mentorUuid;
    private Integer volt;

}
