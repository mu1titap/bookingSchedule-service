package com.multitab.sessionRequest.application.port.in;

import com.multitab.sessionRequest.adaptor.out.feignClient.dto.SessionResponseOutDto;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
class ServiceCallUseCaseTest {
    @Autowired
    MentoringServiceCallUseCase serviceCallUseCase;

    @Test
    void getSessionOutDtoByUuid(){
        SessionResponseOutDto sessionResponseOutDto =
                serviceCallUseCase.getSessionOutDtoByUuid("ac419217-cb98-4334-8b78-8126aa0e57aa");
        log.info("sessionResponseOutDto : "+sessionResponseOutDto);
    }

}