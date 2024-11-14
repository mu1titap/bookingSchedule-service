package com.multitab.sessionRequest.adaptor.out.redis.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class RedisSessionLockRepository {
    private final RedisTemplate<String, String> redisTemplate;

    // 세션에 대한 락
    public Boolean sessionLock(String sessionUuid) {
        return redisTemplate
                .opsForValue()
                .setIfAbsent(generateSessionKey(sessionUuid),
                        "lock", Duration.ofMillis(3_000)); // 최대 3초 동안 락 유지
    }
    // 세션에 대한 락 해제
    public Boolean sessionUnlock(String sessionUuid) {
        return redisTemplate.delete(generateSessionKey(sessionUuid));
    }

    //  세션락 에 대한 고유 키 생성
    private String generateSessionKey(String sessionUuid) {
        return "session:lock:" + sessionUuid;
    }
}
