package com.multitab.sessionRequest.adaptor.out.feignClient.dto;

import lombok.*;

import java.time.LocalDate;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SessionResponseOutDto {
    private Integer maxHeadCount;
    private LocalDate deadlineDate;
    private Boolean isClosed;
    private LocalDate startDate; // 세션 참가 신청 쉬소 시 , 캘린더 read data 업데이트에 필요
}
