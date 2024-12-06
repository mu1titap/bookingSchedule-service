package com.multitab.sessionRequest.domain.model;

import com.multitab.sessionRequest.application.port.out.dto.out.SessionUserResponseOutDto;
import com.multitab.sessionRequest.common.entity.BaseResponseStatus;
import com.multitab.sessionRequest.common.exception.BaseException;
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
    private String id;

    private String sessionUuid;

    private String menteeUuid;
    private Status status;


    /**
     * 세션 참가 신청 도메인 생성 메서드
     */
    // 최초 세션 참가 신청
    public static SessionRequestDomain createSessionRequestDomain(String sessionUuid, String menteeUuid) {
        return SessionRequestDomain.builder()
                .sessionUuid(sessionUuid)
                .menteeUuid(menteeUuid)
                .status(Status.PENDING) // 대기 상태로 생성
                .build();
    }
    // 취소 후 다시 세션 참가 신청
    public static SessionRequestDomain reCreateSessionRequestDomain(String sessionUuid,
                                                                    String menteeUuid,
                                                                    String sessionUserId) {
        return SessionRequestDomain.builder()
                .id(sessionUserId)
                .sessionUuid(sessionUuid)
                .menteeUuid(menteeUuid)
                .status(Status.PENDING) // 대기 상태로 생성
                .build();
    }


    // 세션요청도메인 사용자 취소 상태 변경 메서드
    public static SessionRequestDomain createCancelSessionUser(SessionUserResponseOutDto sessionUserOut) {
        if(sessionUserOut == null){
            //throw new IllegalArgumentException("세션 참가 이력 없음");
            throw new BaseException(BaseResponseStatus.IS_NOT_HISTORY);
        }
        String findSessionUserId = sessionUserOut.getId();
        if( sessionUserOut.getStatus() == Status.CANCELLED_BY_USER){
            //throw new IllegalArgumentException("이미 취소된 세션");
            throw new BaseException(BaseResponseStatus.ALREADY_CANCEL); // 이미 취소된 세션
        }
        if( sessionUserOut.getStatus() != Status.PENDING){
            //throw new IllegalArgumentException("세션 참가 등록을 취소할 수 있는 상태가 아님");
            throw new BaseException(BaseResponseStatus.INVALID_SESSION_USER_STATUS);
        }
        return SessionRequestDomain.builder()
                    .id(findSessionUserId)
                    .sessionUuid(sessionUserOut.getSessionUuid())
                    .menteeUuid(sessionUserOut.getMenteeUuid())
                    .status(Status.CANCELLED_BY_USER) // 유저 취소 상태 변경
                    .build();
    }

    // 예약마감일 검사
    public static void isDeadlineValid(LocalDate deadlineDate) {
        if (LocalDate.now().isAfter(deadlineDate)) {
            //throw new IllegalArgumentException("예약마감일 경과 " + deadlineDate);
            throw new BaseException(BaseResponseStatus.DEAD_LINE_PASSED);
        }
    }
    public static void isValidSessionState(Boolean isClosed) {
        if (isClosed) {
            //throw new IllegalArgumentException("닫힌 세션");
            throw new BaseException(BaseResponseStatus.ALREADY_CLOSE_SESSION);
        }
    }

    // 최대 신청인원수 + 멘티중복신청 검사
    public static void validateMenteeAndMaxHeadCount(
            List<SessionUserResponseOutDto> sessionUserOutDtos,
            String registerUuid,
            Integer maxHeadCount )
    {
        if( sessionUserOutDtos != null && sessionUserOutDtos.size() >= maxHeadCount){
            //throw new IllegalArgumentException("최대 신청인원수 초과");
            throw new BaseException(BaseResponseStatus.FULL_SESSION_USER);
        }
        for (SessionUserResponseOutDto sessionUserOutDto : sessionUserOutDtos) {
            if (sessionUserOutDto.getMenteeUuid().equals(registerUuid)) {
                //throw new IllegalArgumentException("중복 신청");
                throw new BaseException(BaseResponseStatus.DUPLICATE_SESSION_REQUEST);
            }
        }
    }
    // 세션참가등록 후 최대모집인원 다 찼는지 검사
    public Boolean isClosedSession(Integer sessionUserListLen, Integer maxHeadCount ) {
        return sessionUserListLen + 1 == maxHeadCount;
    }
}
