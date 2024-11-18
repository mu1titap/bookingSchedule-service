package com.multitab.sessionRequest.application.port.in.dto;

import com.multitab.sessionRequest.application.port.out.dto.in.CancelSessionOutDto;
import com.multitab.sessionRequest.application.port.out.dto.out.AfterSessionUserOutDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CancelSessionDto {
    private String sessionUuid;

    private String menteeUuid;

    private LocalDate deadlineDate;

}
