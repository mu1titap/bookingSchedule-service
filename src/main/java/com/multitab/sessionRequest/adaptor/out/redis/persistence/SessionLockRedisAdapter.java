package com.multitab.sessionRequest.adaptor.out.redis.persistence;

import com.multitab.sessionRequest.adaptor.out.redis.repository.RedisSessionLockRepository;
import com.multitab.sessionRequest.application.port.out.SessionRockRepositoryOutPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Log4j2
@Component("SessionLockRedisAdapter")
public class SessionLockRedisAdapter implements SessionRockRepositoryOutPort {
    private final RedisSessionLockRepository redisSessionLockRepository;
    @Override
    public Boolean sessionRock(String sessionUuid) {
        return redisSessionLockRepository.sessionLock(sessionUuid);
    }

    @Override
    public Boolean sessionUnRock(String sessionUuid) {
        return redisSessionLockRepository.sessionUnlock(sessionUuid);
    }
}
