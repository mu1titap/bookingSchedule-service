package com.multitab.sessionRequest.application.service;

import com.multitab.sessionRequest.application.port.in.RegisterSessionUserUseCase;
import com.multitab.sessionRequest.application.port.in.SessionRockUseCase;
import com.multitab.sessionRequest.application.port.in.dto.RegisterSessionDto;
import com.multitab.sessionRequest.application.port.out.SessionRockRepositoryOutPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class SessionRockService implements SessionRockUseCase {
    private final RegisterSessionUserUseCase registerSessionUserUseCase;
    private final SessionRockRepositoryOutPort sessionRockRepositoryOutPort;

    @Override
    public void registerSessionUser(RegisterSessionDto sessionRequestDto) throws InterruptedException {
        String key = sessionRequestDto.getSessionUuid();
        while (!sessionRockRepositoryOutPort.sessionRock(key)) {
            Thread.sleep(50);
        }
        try {
            registerSessionUserUseCase.registerSessionUser(sessionRequestDto);
        } finally {
            sessionRockRepositoryOutPort.sessionUnRock(key);
        }
    }
}
