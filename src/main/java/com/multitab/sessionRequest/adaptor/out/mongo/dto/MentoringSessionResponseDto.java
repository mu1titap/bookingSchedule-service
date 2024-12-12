package com.multitab.sessionRequest.adaptor.out.mongo.dto;

import jakarta.annotation.security.DenyAll;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "mentoring_session")
public class MentoringSessionResponseDto {
    @Id
    private String id;
    private String mentorUuid;
    private String price;
    private String sessionUuid;
}
