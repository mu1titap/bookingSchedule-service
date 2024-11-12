package com.multitab.sessionRequest.application.port.out.dto;

import com.multitab.sessionRequest.adaptor.out.mysql.entity.SessionUserEntity;
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
    private String sessionUuid;

    private String menteeUuid;

    public static List<SessionUserResponseOutDto> from(List<SessionUserEntity> entities){
        return entities.stream().map(
                entity -> SessionUserResponseOutDto.builder()
                        .sessionUuid(entity.getSessionUuid())
                        .menteeUuid(entity.getMenteeUuid())
                        .build()
        ).toList();
    }
}
