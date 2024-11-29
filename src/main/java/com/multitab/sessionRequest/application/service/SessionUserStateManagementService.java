package com.multitab.sessionRequest.application.service;

import com.multitab.sessionRequest.application.port.in.SessionUserStatusManagementUseCase;
import com.multitab.sessionRequest.application.port.out.SendMessageOutPort;
import com.multitab.sessionRequest.application.port.out.SessionUserInqueryRepositoryOutPort;
import com.multitab.sessionRequest.application.port.out.SessionUserStatusManagementOutPort;
import com.multitab.sessionRequest.application.port.out.dto.out.EndSessionMessage;
import com.multitab.sessionRequest.application.port.out.dto.out.SessionUserResponseOutDto;
import com.multitab.sessionRequest.application.port.out.dto.out.SessionUserUpdateMessage;
import com.multitab.sessionRequest.domain.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
@Transactional
public class SessionUserStateManagementService implements SessionUserStatusManagementUseCase {
    private final SessionUserStatusManagementOutPort sessionUserStatusManagementOutPort;
    private final SessionUserInqueryRepositoryOutPort sessionUserInqueryRepositoryOutPort;
    private final SendMessageOutPort sendMessageOutPort;
    @Override
    public void endMentoringSession(String sessionUuid , LocalDate startDate) {
        // 확정 상태인 참가자 리스트 조회
        List<SessionUserResponseOutDto> confirmedSessionUserList =
                sessionUserInqueryRepositoryOutPort.getConfirmedSessionUser(sessionUuid);
        List<String> sessionUserIdList = confirmedSessionUserList.stream()
                .map(SessionUserResponseOutDto::getId)
                .toList();

        // 멘토링 세션 참가자 상태 [확정] -> [종료] 업데이트
        sessionUserStatusManagementOutPort.endSessionUserState(sessionUserIdList);

        // 세션 종료 메시지 전송
        sendMessageOutPort.sendEndSessionMessage("end-session", EndSessionMessage.builder().sessionUuid(sessionUuid).build());

        // 세션 참가자 리스트 상태 변경 이벤트
        List<SessionUserUpdateMessage> sessionUserUpdateMessageList =
                confirmedSessionUserList.stream()
                        .map(sessionUser ->
                                getSessionUserUpdateMessage(sessionUser.getMenteeUuid(), startDate,
                                        sessionUser.getSessionUuid(), Status.END))
                        .toList();
        // 유저마다 세션 참여 상태 업데이트 메시지 전송
        log.info("세션종료 유저 상태 전송");
        sessionUserUpdateMessageList.forEach(
                sessionUserUpdateMessage -> {
                        log.info(sessionUserUpdateMessage);
                        sendMessageOutPort.sendUpdateSessionUserMessage("update-session-user", sessionUserUpdateMessage);
                }
        );


    }

    public SessionUserUpdateMessage getSessionUserUpdateMessage(String userUuid, LocalDate startDate,
                                                                String sessionUuid, Status status) {
        return SessionUserUpdateMessage.builder()
                .userUuid(userUuid)
                .startDate(startDate)
                .sessionUuid(sessionUuid)
                .status(status).build();
    }

}
