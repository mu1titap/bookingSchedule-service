package com.multitab.sessionRequest.adaptor.out.mongo.repository;

import com.multitab.sessionRequest.adaptor.out.mongo.dto.MentoringSessionResponseDto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MentoringSessionMongoRepository extends
    MongoRepository<MentoringSessionResponseDto, String> {
    MentoringSessionResponseDto findBySessionUuid(String sessionUuid);

}
