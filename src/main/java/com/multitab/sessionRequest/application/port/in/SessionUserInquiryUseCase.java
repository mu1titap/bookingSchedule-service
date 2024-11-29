package com.multitab.sessionRequest.application.port.in;

import com.multitab.sessionRequest.application.port.out.dto.out.SessionUserResponseOutDto;
import com.multitab.sessionRequest.domain.Status;

import java.util.List;

public interface SessionUserInquiryUseCase {
    // 세션의 참가자 리스트 조회
    List<SessionUserResponseOutDto> getSessionUserOutDtoBySessionUuid(String sessionUuid, Status status);

    Integer getCountBySessionUsers(String sessionUuid, Status status);

    // 세션의 참가 유저(멘티) 조회
    SessionUserResponseOutDto getSessionUserOutDtoBySessionUuidAndMenteeUuid(String sessionUuid,
                                                                             String menteeUuid);


}
