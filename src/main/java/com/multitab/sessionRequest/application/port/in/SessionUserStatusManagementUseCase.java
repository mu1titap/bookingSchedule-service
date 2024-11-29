package com.multitab.sessionRequest.application.port.in;

import java.time.LocalDate;

public interface SessionUserStatusManagementUseCase {
    void endMentoringSession(String sessionUuid, LocalDate startDate);
}


