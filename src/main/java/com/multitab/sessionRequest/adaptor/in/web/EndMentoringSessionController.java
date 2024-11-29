package com.multitab.sessionRequest.adaptor.in.web;

import com.multitab.sessionRequest.adaptor.in.web.mapper.RegisterSessionVoMapper;
import com.multitab.sessionRequest.adaptor.in.web.vo.in.EndSessionRequestVo;
import com.multitab.sessionRequest.adaptor.in.web.vo.in.RegisterSessionVo;
import com.multitab.sessionRequest.application.port.in.SessionRockUseCase;
import com.multitab.sessionRequest.application.port.in.SessionUserStatusManagementUseCase;
import com.multitab.sessionRequest.common.entity.BaseResponse;
import com.multitab.sessionRequest.common.entity.BaseResponseStatus;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/session-request-service")
public class EndMentoringSessionController {
    private final SessionUserStatusManagementUseCase sessionUserStatusManagementUseCase;

    @Operation(summary = "세션 종료 처리" , description = "- 멘토링 세션 진행 완료 되면 멘토가 요청함. <br/>"+
            "**중요**<br/>"+ "startDate는 멘토링 세션 시작일 이어야 합니다. 테스트 시에도 이를 맞추지 않고 오늘 날짜를 넣어버리면 리드 데이터 동기화 문제 생길 수 있음"
            ,tags = {"멘토링 세션 종료"})
    @PutMapping("session-end")
    public BaseResponse<Void> endMentoringSession(
            @RequestBody EndSessionRequestVo requestVo) {

        sessionUserStatusManagementUseCase.endMentoringSession(requestVo.getSessionUuid(), requestVo.getStartDate());
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }
}

