package com.multitab.sessionRequest.application.port.out.dto;

import com.multitab.sessionRequest.adaptor.out.mysql.entity.SessionUserEntity;
import com.multitab.sessionRequest.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
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
}
