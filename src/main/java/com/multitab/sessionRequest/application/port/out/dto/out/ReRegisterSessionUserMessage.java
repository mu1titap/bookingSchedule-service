package com.multitab.sessionRequest.application.port.out.dto.out;

import lombok.*;

import java.time.LocalDate;
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ReRegisterSessionUserMessage {
    private String sessionUuid;

    private String menteeUuid;
    private String menteeImageUrl;

    private LocalDate startDate;

    private Boolean shouldCloseSession;
}
