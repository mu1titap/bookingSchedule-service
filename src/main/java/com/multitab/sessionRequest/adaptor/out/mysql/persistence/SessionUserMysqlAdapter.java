package com.multitab.sessionRequest.adaptor.out.mysql.persistence;

import com.multitab.sessionRequest.adaptor.out.mysql.entity.SessionUserEntity;
import com.multitab.sessionRequest.adaptor.out.mysql.repository.SessionUserJpaRepository;
import com.multitab.sessionRequest.application.port.out.SessionUserRepositoryOutPort;
import com.multitab.sessionRequest.application.port.out.dto.SessionUserResponseOutDto;
import com.multitab.sessionRequest.application.port.out.dto.out.AfterSessionUserOutDto;
import com.multitab.sessionRequest.application.port.out.dto.out.RegisterSessionOutDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Log4j2
@Component("SessionUserMysqlAdapter")
public class SessionUserMysqlAdapter implements SessionUserRepositoryOutPort {
    private final SessionUserJpaRepository sessionUserJpaRepository;
    @Override
    public List<SessionUserResponseOutDto> getSessionsUserBySessionUuid(String sessionUuid) {
        return SessionUserResponseOutDto.from(sessionUserJpaRepository.findAllBySessionUuid(sessionUuid));
    }

    @Override
    public AfterSessionUserOutDto registerSessionUser(RegisterSessionOutDto registerSessionOutDto) {
        return AfterSessionUserOutDto.from(sessionUserJpaRepository.save(registerSessionOutDto.toJpaEntity()));
    }
}
