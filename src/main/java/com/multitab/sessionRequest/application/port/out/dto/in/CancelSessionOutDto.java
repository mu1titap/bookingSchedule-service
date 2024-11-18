package com.multitab.sessionRequest.application.port.out.dto.in;

import com.multitab.sessionRequest.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CancelSessionOutDto {
    private String sessionUserId;
    private Status status;
}
