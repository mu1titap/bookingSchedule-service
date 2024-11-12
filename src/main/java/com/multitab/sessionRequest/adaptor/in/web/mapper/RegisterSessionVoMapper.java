package com.multitab.sessionRequest.adaptor.in.web.mapper;

import com.multitab.sessionRequest.adaptor.in.web.vo.RegisterSessionVo;
import com.multitab.sessionRequest.application.port.in.dto.RegisterSessionDto;
import org.springframework.stereotype.Component;

@Component
public class RegisterSessionVoMapper {
    public static RegisterSessionDto from (RegisterSessionVo vo) {
        return RegisterSessionDto.builder()
                .menteeUuid(vo.getMenteeUuid())
                .sessionUuid(vo.getSessionUuid())
                .mentoringName(vo.getMentoringName())
                .build();
    }
}
