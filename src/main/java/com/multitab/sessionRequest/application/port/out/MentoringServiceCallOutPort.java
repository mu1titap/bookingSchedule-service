package com.multitab.sessionRequest.application.port.out;

import com.multitab.sessionRequest.adaptor.out.feignClient.dto.SessionResponseOutDto;

public interface MentoringServiceCallOutPort {
    SessionResponseOutDto getSessionResponseOutDtoByUuid(String uuid);

    void closeSessionByUuid(String uuid);
}
