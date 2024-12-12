package com.multitab.sessionRequest.application.port.out.dto.out;

import com.multitab.sessionRequest.adaptor.out.mysql.entity.SessionUserEntity;
import com.multitab.sessionRequest.domain.Status;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AfterSessionUserOutDto {
    private String id; // 세션유저 Id
    private String sessionUuid;
    @Setter
    private String mentorUuid;

    private String menteeUuid;
    @Setter
    private String nickName;
    @Setter
    private String menteeImageUrl;
    private Status status;
    @Setter
    private String mentoringName;
    @Setter
    private Boolean isClosed;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // jpa 엔티티 -> AfterSessionUserOutDto
    public static AfterSessionUserOutDto from(SessionUserEntity entity){
        return AfterSessionUserOutDto.builder()
                .id(entity.getId().toString())
                .sessionUuid(entity.getSessionUuid())
                .menteeUuid(entity.getMenteeUuid())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

}
