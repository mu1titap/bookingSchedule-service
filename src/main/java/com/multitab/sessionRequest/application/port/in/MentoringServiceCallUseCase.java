package com.multitab.sessionRequest.application.port.in;

import com.multitab.sessionRequest.adaptor.out.feignClient.dto.SessionResponseOutDto;

public interface MentoringServiceCallUseCase {
    SessionResponseOutDto getSessionOutDtoByUuid(String sessionUuid);

    void closeSession(String uuid);

    void openSession(String uuid);
}
