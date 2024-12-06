package com.multitab.sessionRequest.application.port.out.dto.out;

import com.multitab.sessionRequest.domain.Status;
import lombok.*;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SessionUserUpdateMessage {
    private String userUuid;
    private LocalDate startDate;
    private String sessionUuid;
    private Status status;
}
