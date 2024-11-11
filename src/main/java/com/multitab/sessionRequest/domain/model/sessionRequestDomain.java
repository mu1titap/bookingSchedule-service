package com.multitab.sessionRequest.domain.model;

import com.multitab.sessionRequest.domain.Status;
import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class sessionRequestDomain {
    private String uuid;
    private String sessionUuid;
    private String menteeUuid;
    private Status status;

    private LocalDate deadlineDate; // 예약 마감일

}
