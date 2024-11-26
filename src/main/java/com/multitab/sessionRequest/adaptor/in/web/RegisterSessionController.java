package com.multitab.sessionRequest.adaptor.in.web;

import com.multitab.sessionRequest.adaptor.in.web.mapper.RegisterSessionVoMapper;
import com.multitab.sessionRequest.adaptor.in.web.vo.in.RegisterSessionVo;
import com.multitab.sessionRequest.application.port.in.RegisterSessionUserUseCase;
import com.multitab.sessionRequest.application.port.in.SessionRockUseCase;
import com.multitab.sessionRequest.common.entity.BaseResponse;
import com.multitab.sessionRequest.common.entity.BaseResponseStatus;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/session-request-service")
public class RegisterSessionController {
    private final SessionRockUseCase sessionRockUseCase;

    @Operation(summary = "세션 참가 등록" , description = "- 멘토링 세션 참가 요청 <br>" +
            " 1. 닫힌 세션(isClosed)은 세션 참가 등록 불가 <br>" +
            " 2. 세션에 설정된 예약 마감일 까지의 세션만 참가 등록 가능 <br>" +
            " 3. 멘티의 중복 신청 불가 <br>" +
            " 4. 세션에 설정된 최대 인원수 까지만 등록 받음 <br>"+
            " 5. 멘티의 다른 세션참여 등록 시간과 겹칠 경우 불가 (예정) <br>"+
            " 참고 : 인자 중에서 mentoringName 는 직접적으로 멘토링 세션 참가 등록 기능에 필요한 데이터는 아니지만 스케줄 read data 업데이트에 필요함"
            ,tags = {"멘토링 세션 참가"})
    @PostMapping("")
    public BaseResponse<Void> createMentoringAndSession(@RequestHeader("userUuid") String userUuid,
                                                        @RequestBody RegisterSessionVo request) throws InterruptedException {

        sessionRockUseCase.registerSessionUser(RegisterSessionVoMapper.of(userUuid,request));
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }
}

