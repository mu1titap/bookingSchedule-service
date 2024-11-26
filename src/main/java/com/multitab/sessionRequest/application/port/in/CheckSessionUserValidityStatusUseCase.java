package com.multitab.sessionRequest.application.port.in;

/**
 * 세션 참가자 참가 상태 유효한지 확인 유스케이스
 */
public interface CheckSessionUserValidityStatusUseCase {
    boolean checkSessionUserValidityStatus(String sessionUuid, String userUuid);
}
