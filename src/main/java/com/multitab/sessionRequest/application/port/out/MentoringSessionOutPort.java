package com.multitab.sessionRequest.application.port.out;

import com.multitab.sessionRequest.adaptor.out.mongo.dto.MentoringSessionResponseDto;

public interface MentoringSessionOutPort {
    MentoringSessionResponseDto findMentorUuidBySessionUuid(String sessionUuid);
}
