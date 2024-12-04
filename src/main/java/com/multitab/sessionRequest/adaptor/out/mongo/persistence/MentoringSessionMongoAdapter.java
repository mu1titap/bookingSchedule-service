package com.multitab.sessionRequest.adaptor.out.mongo.persistence;

import com.multitab.sessionRequest.adaptor.out.mongo.dto.MentoringSessionResponseDto;
import com.multitab.sessionRequest.adaptor.out.mongo.repository.MentoringSessionMongoRepository;
import com.multitab.sessionRequest.application.port.out.dto.MentoringSessionRepositoryOutPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@RequiredArgsConstructor
@Component("MentoringSessionMongoAdapter")
public class MentoringSessionMongoAdapter implements MentoringSessionRepositoryOutPort {

    private final MentoringSessionMongoRepository mentoringSessionMongoRepository;
    @Override
    public MentoringSessionResponseDto getMentorUuidBySessionUuid(String sessionUuid) {
        return mentoringSessionMongoRepository.findBySessionUuid(sessionUuid);
    }



}
