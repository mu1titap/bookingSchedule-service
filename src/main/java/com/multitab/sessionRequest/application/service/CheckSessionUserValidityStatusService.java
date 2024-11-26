package com.multitab.sessionRequest.application.service;


import com.multitab.sessionRequest.application.port.in.CheckSessionUserValidityStatusUseCase;
import com.multitab.sessionRequest.application.port.out.CheckSessionUserValidityStatusOutPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@RequiredArgsConstructor
@Service
@Transactional
public class CheckSessionUserValidityStatusService implements CheckSessionUserValidityStatusUseCase {
    private final CheckSessionUserValidityStatusOutPort checkSessionUserValidityStatusOutPort;
    @Override
    public boolean checkSessionUserValidityStatus(String sessionUuid, String userUuid) {
        return checkSessionUserValidityStatusOutPort.checkSessionUserValidityStatus(sessionUuid, userUuid);
    }
}
