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
}
