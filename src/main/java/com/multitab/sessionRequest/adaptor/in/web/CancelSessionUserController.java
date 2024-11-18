package com.multitab.sessionRequest.adaptor.in.web;

import com.multitab.sessionRequest.adaptor.in.web.mapper.RegisterSessionVoMapper;
import com.multitab.sessionRequest.adaptor.in.web.vo.in.CancelSessionVo;
import com.multitab.sessionRequest.adaptor.in.web.vo.in.RegisterSessionVo;
import com.multitab.sessionRequest.application.port.in.CancelSessionUserUseCase;
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
public class CancelSessionUserController {
    private final CancelSessionUserUseCase cancelSessionUserUseCase;

    @Operation(summary = "세션 참가 등록 취소" , description = "- 멘토링 세션 참가 취소 <br>" +
            " 1. 참가 신청 이력이 존재해야 함 <br>" +
            " 2. 이미 해당 세션의 참가 취소 이력이 존재하면 불가능" +
            " 3. 예약마감일이 지나면 불가능. 즉 [대기] 상태여야만 취소 가능"
            ,tags = {"멘토링 세션 참가"})
    @PutMapping("")
    public BaseResponse<Void> cancelSessionUser(@RequestHeader("userUuid") String userUuid,
                                                @RequestBody CancelSessionVo request){
        cancelSessionUserUseCase.cancelSessionUser(RegisterSessionVoMapper.of(userUuid,request));
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }
}

