package com.multitab.sessionRequest.adaptor.out.feignClient.vo;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SessionPaymentVo {

    private String sessionUuid;
    private String menteeUuid;
    private String mentorUuid;
    private Integer volt;

}
