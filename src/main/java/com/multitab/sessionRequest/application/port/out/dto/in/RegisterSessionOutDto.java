package com.multitab.sessionRequest.application.port.out.dto.in;

import com.multitab.sessionRequest.adaptor.out.mysql.entity.SessionUserEntity;
import com.multitab.sessionRequest.domain.Status;
import com.multitab.sessionRequest.domain.model.SessionRequestDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterSessionOutDto {
    private String sessionUuid;

    private String menteeUuid;

    private Status status;

    private String mentoringName;

    // 도메인 -> 아웃포트 dto
    public static RegisterSessionOutDto from(SessionRequestDomain domain){
        return RegisterSessionOutDto.builder()
                .sessionUuid(domain.getSessionUuid())
                .menteeUuid(domain.getMenteeUuid())
                .status(domain.getStatus())
                .mentoringName(domain.getMentoringName())
                .build();
    }
    // 아웃포트 dto -> jpa 엔티티
    public SessionUserEntity toJpaEntity(){
        return SessionUserEntity.builder()
                .sessionUuid(this.sessionUuid)
                .menteeUuid(this.menteeUuid)
                .status(this.status)
                .build();
    }


}
