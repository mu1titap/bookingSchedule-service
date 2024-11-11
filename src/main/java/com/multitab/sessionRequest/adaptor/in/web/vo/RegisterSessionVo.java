package com.multitab.sessionRequest.adaptor.in.web.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class RegisterSessionVo {
    @Schema(description = "세션 uuid", nullable = false)
    private String sessionUuid;
    @Schema(description = "멘티 uuid", nullable = false)
    private String menteeUuid;
}
