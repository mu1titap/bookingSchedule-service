package com.multitab.sessionRequest.application.port.in;

import com.multitab.sessionRequest.adaptor.in.kafka.dto.DeadlinePastSessionResponseOutDto;

public interface DeadlinePastSessionUpdateUseCase {
    /**
     * 1. 세션 참여 대기 상태 유저 리스트 조회 후 최소인원 이상 이면 확정, 아니면 취소 상태로 업데이트
     * 2. 세션 진행 확정여부 이벤트 메시지 발행
     * 3. 대기 상태 유저마다 세션 참여 대기 상태 업데이트 메시지 발행
     */
    void updateSessionUser(DeadlinePastSessionResponseOutDto dto);

}
