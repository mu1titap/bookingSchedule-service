package com.multitab.sessionRequest.adaptor.in.web.vo.in;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class RegisterSessionVo {
    //
    @Schema(description = "세션 uuid", nullable = false)
    private String sessionUuid;
    @Schema(description = "멘토 uuid", nullable = false)
    private String mentorUuid;

    @Schema(description = "볼트(가격)", nullable = false)
    private Integer volt;


    @Schema(description = "유저 닉네임", nullable = false)
    private String nickName;
    @Schema(description = "유저 프로필 url", nullable = false)
    private String userImageUrl;

    @Schema(description = "멘토링 이름", nullable = false)
    private String mentoringName;
}
