package com.multitab.sessionRequest.application.port.out.dto.out;

import lombok.*;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CancelSessionUserMessage {
    private String sessionUuid;

    private String menteeUuid;

    private LocalDate startDate;

    private Boolean shouldOpenSession;
}
