package com.multitab.sessionRequest.adaptor.out.feignClient;

import com.multitab.sessionRequest.adaptor.out.feignClient.dto.SessionResponseOutDto;
import com.multitab.sessionRequest.application.port.out.MentoringServiceCallOutPort;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Log4j2
@Component("MentoringServiceCallOutAdapter")
public class MentoringServiceCallOutAdapter implements MentoringServiceCallOutPort {
    private final MentoringServiceFeignClient mentoringServiceClient;
    @Override
    public SessionResponseOutDto getSessionResponseOutDtoByUuid(String uuid) {
        return mentoringServiceClient.getSession(uuid);
    }

    @Override
    public void closeSessionByUuid(String uuid) {
        try {
            mentoringServiceClient.closeSession(uuid);
        } catch (FeignException e) {
            log.error("FeignException error => closeSession", e);
        }
    }

    @Override
    public void openSessionByUuid(String uuid) {
        try {
            mentoringServiceClient.openSession(uuid);
        } catch (FeignException e) {
            log.error("FeignException error => openSession", e);
        }
    }

}
