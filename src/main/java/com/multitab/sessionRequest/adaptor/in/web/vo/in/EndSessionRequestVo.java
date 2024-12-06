package com.multitab.sessionRequest.adaptor.in.web.vo.in;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class EndSessionRequestVo {
    @Schema(description = "세션 uuid", nullable = false)
    private String sessionUuid;
    @Schema(description = "세션 시작 날짜 (오늘날짜 x)", nullable = false)
    private LocalDate startDate;
}
