package com.multitab.sessionRequest.adaptor.out.feignClient;

import com.multitab.sessionRequest.adaptor.out.feignClient.dto.SessionResponseOutDto;
import feign.FeignException;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
class MentoringServiceClientTest {

    @Autowired
    MentoringServiceFeignClient mentoringServiceClient;

    @Test
    void getSessionTest() {
        SessionResponseOutDto session = null;
        try {
              session = mentoringServiceClient.getSession("ac419217-cb98-4334-8b78-8126aa0e57aa");
        } catch (FeignException ex) {
            log.error(ex.getMessage());
        }
        log.info("session : "+session);
    }

}