package com.multitab.sessionRequest.application.port.out.dto.out;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SessionConfirmedMessage {
    private String sessionUuid;
    private Boolean sessionIsConfirmed;
}
