package com.multitab.sessionRequest.application.port.out;

public interface CheckSessionUserValidityStatusOutPort {
    boolean checkSessionUserValidityStatus(String sessionUuid, String userUuid);
}
