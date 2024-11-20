package com.multitab.sessionRequest.application.scheduler;

import com.multitab.sessionRequest.application.port.in.SessionUserInquiryUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@RequiredArgsConstructor
@Log4j2
public class scheduler {

    //@Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
    @Scheduled(cron = "0 * * * * *", zone = "Asia/Seoul")
    public void processSessionsPastDeadline() {
        //System.out.println("test at " + java.time.LocalDateTime.now());

    }
}
