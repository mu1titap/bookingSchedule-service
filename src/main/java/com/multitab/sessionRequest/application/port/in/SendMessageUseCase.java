package com.multitab.sessionRequest.application.port.in;

import com.multitab.sessionRequest.application.port.out.dto.out.AfterSessionUserOutDto;
import com.multitab.sessionRequest.application.port.out.dto.out.CancelSessionUserMessage;
import com.multitab.sessionRequest.application.port.out.dto.out.ReRegisterSessionUserMessage;
import com.multitab.sessionRequest.application.port.out.dto.out.SessionConfirmedMessage;

public interface SendMessageUseCase {
    // "멘토링 세션 참여 등록 메시지 전송"
    void sendRegisterSessionUserMessage(String topic , AfterSessionUserOutDto afterSessionUserOutDto);

    // "멘토링 세션 참여 등록 취소 메시지 전송"
    void sendCancelRegisterSessionUserMessage(String topic , CancelSessionUserMessage cancelSessionUserMessage);

    // "멘토링 세션 참여 '재'등록 메시지 전송"
    void sendReRegisterSessionUserMessage(String topic , ReRegisterSessionUserMessage reRegisterSessionUserMessage);

    // "세션 확정 이벤트 전송"
    void sendConfirmSessionMessage(String topic , SessionConfirmedMessage sessionConfirmedMessage);

}
