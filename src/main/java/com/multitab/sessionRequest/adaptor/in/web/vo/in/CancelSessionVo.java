package com.multitab.sessionRequest.adaptor.in.web.vo.in;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CancelSessionVo {
    @Schema(description = "세션 uuid", nullable = false)
    private String sessionUuid;
//    @Schema(description = "멘티 uuid", nullable = false)
//    private String menteeUuid;
    @Schema(description = "예약마감일 (yyyy-MM-dd)")
    private LocalDate deadlineDate;

}
