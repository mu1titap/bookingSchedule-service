package com.multitab.sessionRequest.adaptor.in.web.mapper;

import com.multitab.sessionRequest.adaptor.in.web.vo.in.CancelSessionVo;
import com.multitab.sessionRequest.adaptor.in.web.vo.in.RegisterSessionVo;
import com.multitab.sessionRequest.application.port.in.dto.CancelSessionDto;
import com.multitab.sessionRequest.application.port.in.dto.RegisterSessionDto;
import org.springframework.stereotype.Component;

@Component
public class RegisterSessionVoMapper {
    public static RegisterSessionDto of (String userUuid,RegisterSessionVo vo) {
        return RegisterSessionDto.builder()
                .menteeUuid(userUuid)
                .sessionUuid(vo.getSessionUuid())
                .mentoringName(vo.getMentoringName())
                .build();
    }
    public static CancelSessionDto of (String userUuid, CancelSessionVo vo){
        return CancelSessionDto.builder()
                .sessionUuid(vo.getSessionUuid())
                .menteeUuid(userUuid)
                .deadlineDate(vo.getDeadlineDate())
                .build();
    }


}
