package com.multitab.sessionRequest.adaptor.in.web;

import com.multitab.sessionRequest.application.port.in.CheckSessionUserValidityStatusUseCase;
import com.multitab.sessionRequest.common.entity.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/session-request-service")
public class CheckSessionUserController {
    private final CheckSessionUserValidityStatusUseCase checkSessionUserValidityStatusUseCase;

    @Operation(summary = "세션 참가 여부 확인" , description = "세션 참가 리스트에서 확정 상태면 true, 아니면 false"
            ,tags = {"멘토링 세션 참가"})
    @PostMapping("/validity-status")
    public BaseResponse<Boolean> checkSessionUserValidityStatus(
            @RequestParam("sessionUuid") String sessionUuid, @RequestHeader("userUuid") String userUuid
    ) {;
        return new BaseResponse<>(checkSessionUserValidityStatusUseCase.checkSessionUserValidityStatus(sessionUuid, userUuid));
    }
}
