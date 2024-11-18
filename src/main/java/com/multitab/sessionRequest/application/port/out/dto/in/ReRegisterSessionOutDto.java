package com.multitab.sessionRequest.application.port.out.dto.in;

import com.multitab.sessionRequest.domain.Status;
import com.multitab.sessionRequest.domain.model.SessionRequestDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReRegisterSessionOutDto {
    private String sessionUserId;
    private Status status;

    public static ReRegisterSessionOutDto from(SessionRequestDomain domain){
        return ReRegisterSessionOutDto.builder()
                .sessionUserId(domain.getId())
                .status(domain.getStatus())
                .build();
    }
}
