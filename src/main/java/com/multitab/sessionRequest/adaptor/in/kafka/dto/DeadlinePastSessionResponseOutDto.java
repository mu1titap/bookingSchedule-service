package com.multitab.sessionRequest.adaptor.in.kafka.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DeadlinePastSessionResponseOutDto {
    private Long mentoringId;
    private String mentorName;

    private String mentorUuid;
    private Long sessionId;
    private String sessionUuid;
    private Integer minHeadCount;
    private Integer maxHeadCount;
    private LocalDate startDate;



}
