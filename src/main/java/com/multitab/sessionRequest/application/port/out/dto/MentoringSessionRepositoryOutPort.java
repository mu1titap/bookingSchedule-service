package com.multitab.sessionRequest.application.port.out.dto;

import com.multitab.sessionRequest.adaptor.out.mongo.dto.MentoringSessionResponseDto;

public interface MentoringSessionRepositoryOutPort {
    MentoringSessionResponseDto getMentorUuidBySessionUuid(String sessionUuid);

}
