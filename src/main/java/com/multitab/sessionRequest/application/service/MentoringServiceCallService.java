package com.multitab.sessionRequest.application.service;

import com.multitab.sessionRequest.adaptor.out.feignClient.dto.SessionResponseOutDto;
import com.multitab.sessionRequest.application.port.in.MentoringServiceCallUseCase;
import com.multitab.sessionRequest.application.port.out.MentoringServiceCallOutPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@RequiredArgsConstructor
@Service
@Transactional
public class MentoringServiceCallService implements MentoringServiceCallUseCase {
    private final MentoringServiceCallOutPort mentoringServiceCallOutPort;
    @Override
    public SessionResponseOutDto getSessionOutDtoByUuid(String uuid) {
        return mentoringServiceCallOutPort.getSessionResponseOutDtoByUuid(uuid);
    }

    @Override
    public void closeSession(String uuid) {
        mentoringServiceCallOutPort.closeSessionByUuid(uuid);
    }
}
