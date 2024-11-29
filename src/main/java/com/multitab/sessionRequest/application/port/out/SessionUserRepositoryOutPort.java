package com.multitab.sessionRequest.application.port.out;

import com.multitab.sessionRequest.application.port.out.dto.out.SessionUserResponseOutDto;
import com.multitab.sessionRequest.application.port.out.dto.in.CancelSessionOutDto;
import com.multitab.sessionRequest.application.port.out.dto.in.ReRegisterSessionOutDto;
import com.multitab.sessionRequest.application.port.out.dto.out.AfterSessionUserOutDto;
import com.multitab.sessionRequest.application.port.out.dto.in.RegisterSessionOutDto;
import com.multitab.sessionRequest.domain.Status;

import java.util.List;

public interface SessionUserRepositoryOutPort {
    // 세션 참가 신청
    AfterSessionUserOutDto registerSessionUser(RegisterSessionOutDto registerSessionOutDto);
    // 세션 참가 재신청
    Integer reRegisterSessionUser (ReRegisterSessionOutDto reRegisterSessionOutDto);

    Integer cancelSessionUser(CancelSessionOutDto cancelSessionOutDto);
    //void deadlineUpdateSessionUserStatus(List<String> sessionUserIdList, boolean sessionIsConfirmed);


}
