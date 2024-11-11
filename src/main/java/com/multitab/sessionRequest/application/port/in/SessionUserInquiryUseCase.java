package com.multitab.sessionRequest.application.port.in;

import com.multitab.sessionRequest.application.port.out.dto.SessionUserResponseOutDto;

import java.util.List;

public interface SessionUserInquiryUseCase {
    List<SessionUserResponseOutDto> getSessionUserOutDtoBySessionUuid(String sessionUuid);
}
