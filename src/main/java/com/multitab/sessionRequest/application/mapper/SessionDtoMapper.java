package com.multitab.sessionRequest.application.mapper;

import com.multitab.sessionRequest.application.port.out.dto.in.CancelSessionOutDto;
import com.multitab.sessionRequest.domain.model.SessionRequestDomain;
import org.springframework.stereotype.Component;

@Component
public class SessionDtoMapper {
    public static CancelSessionOutDto from(SessionRequestDomain domain) {
        return CancelSessionOutDto.builder()
                .sessionUserId(domain.getId())
                .status(domain.getStatus())
                .build();
    }
}
