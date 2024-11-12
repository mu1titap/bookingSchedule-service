package com.multitab.sessionRequest.application.port.in;

import com.multitab.sessionRequest.adaptor.out.feignClient.dto.SessionResponseOutDto;

public interface MentoringServiceCallUseCase {
    SessionResponseOutDto getSessionOutDtoByUuid(String uuid);

    void closeSession(String uuid);
}
