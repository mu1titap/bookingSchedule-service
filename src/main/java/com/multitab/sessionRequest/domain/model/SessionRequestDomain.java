package com.multitab.sessionRequest.domain.model;

import com.multitab.sessionRequest.application.port.out.dto.SessionUserResponseOutDto;
import com.multitab.sessionRequest.domain.Status;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SessionRequestDomain {
    private String uuid;

    private String sessionUuid;

    private String menteeUuid;
    private Status status;


    // 세션요청도메인 생성 메서드
    public static SessionRequestDomain createSessionRequestDomain(String sessionUuid, String menteeUuid) {
        return SessionRequestDomain.builder()
                .sessionUuid(sessionUuid)
                .menteeUuid(menteeUuid)
                .status(Status.PENDING) // 대기 상태로 생성
                .build();
    }
    // 예약마감일 검사
    public void isDeadlineValid(LocalDate deadlineDate) {
        if (LocalDate.now().isAfter(deadlineDate)) {
            throw new IllegalArgumentException("예약마감일 경과 " + deadlineDate);
        }
    }
    public void isValidSessionState(Boolean isClosed) {
        if (isClosed) {
            throw new IllegalArgumentException("닫힌 세션");
        }
    }

    // 최대 신청인원수 + 멘티중복신청 검사
    public void isMenteeValid(
            List<SessionUserResponseOutDto> sessionUserOutDtos,
            String registerUuid,
            Integer maxHeadCount )
    {
        if( sessionUserOutDtos.size() >= maxHeadCount){
            throw new IllegalArgumentException("최대 신청인원수 초과");
        }
        for (SessionUserResponseOutDto sessionUserOutDto : sessionUserOutDtos) {
            if (sessionUserOutDto.getMenteeUuid().equals(registerUuid)) {
                throw new IllegalArgumentException("중복 신청");
            }
        }
    }
    // 세션참가등록 후 최대모집인원 다 찼는지 검사
    public Boolean isClosedSession(Integer sessionUserListLen, Integer maxHeadCount ) {
        return sessionUserListLen + 1 == maxHeadCount;
    }
}
