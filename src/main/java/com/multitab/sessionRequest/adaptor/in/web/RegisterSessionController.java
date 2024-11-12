package com.multitab.sessionRequest.adaptor.in.web;

import com.multitab.sessionRequest.adaptor.in.web.mapper.RegisterSessionVoMapper;
import com.multitab.sessionRequest.adaptor.in.web.vo.RegisterSessionVo;
import com.multitab.sessionRequest.application.port.in.RegisterSessionUserUseCase;
import com.multitab.sessionRequest.common.entity.BaseResponse;
import com.multitab.sessionRequest.common.entity.BaseResponseStatus;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/session-request-service")
public class RegisterSessionController {
    private final RegisterSessionUserUseCase registerSessionUserUseCase;
    @Operation(summary = "세션 참가 등록" , description = "멘토링 세션 참가 요청")
    @PostMapping("")
    public BaseResponse<Void> createMentoringAndSession(@RequestBody RegisterSessionVo request) {
        registerSessionUserUseCase.registerSessionUser(RegisterSessionVoMapper.from(request));
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }
}
