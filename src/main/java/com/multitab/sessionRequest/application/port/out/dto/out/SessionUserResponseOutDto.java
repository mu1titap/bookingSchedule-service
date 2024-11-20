package com.multitab.sessionRequest.application.port.out.dto.out;

import com.multitab.sessionRequest.adaptor.out.mysql.entity.SessionUserEntity;
import com.multitab.sessionRequest.domain.Status;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Builder
public class SessionUserResponseOutDto {
    private String id;

    private String sessionUuid;

    private String menteeUuid;

    private Status status;

    public static List<SessionUserResponseOutDto> from(List<SessionUserEntity> entities){
        return entities.stream().map(
                entity -> SessionUserResponseOutDto.builder()
                        .id(entity.getId().toString())
                        .sessionUuid(entity.getSessionUuid())
                        .menteeUuid(entity.getMenteeUuid())
                        .status(entity.getStatus())
                        .build()
        ).toList();
    }

    public static SessionUserResponseOutDto from(SessionUserEntity entity){
        return SessionUserResponseOutDto.builder()
                        .id(entity.getId().toString())
                        .sessionUuid(entity.getSessionUuid())
                        .menteeUuid(entity.getMenteeUuid())
                        .status(entity.getStatus())
                        .build();
    }

    @QueryProjection
    public SessionUserResponseOutDto(String id, String sessionUuid, String menteeUuid, Status status) {
        this.id = id;
        this.sessionUuid = sessionUuid;
        this.menteeUuid = menteeUuid;
        this.status = status;
    }
}
