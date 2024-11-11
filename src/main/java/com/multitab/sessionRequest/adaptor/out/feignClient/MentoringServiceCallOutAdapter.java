package com.multitab.sessionRequest.adaptor.out.feignClient;

import com.multitab.sessionRequest.adaptor.out.feignClient.dto.SessionResponseOutDto;
import com.multitab.sessionRequest.application.port.out.MentoringServiceCallOutPort;
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

}
