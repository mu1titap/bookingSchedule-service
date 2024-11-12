package com.multitab.sessionRequest.adaptor.out.mysql.entity;

import com.multitab.sessionRequest.common.entity.BaseEntity;
import com.multitab.sessionRequest.domain.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "session_user")
@ToString
public class SessionUserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    @Comment("세션 Uuid")
    private String sessionUuid;

    @Column(nullable = false, length = 50)
    @Comment("멘티 Uuid")
    private String menteeUuid;

    @Enumerated(EnumType.STRING)
    private Status status; // 세션 참가 상태
}
