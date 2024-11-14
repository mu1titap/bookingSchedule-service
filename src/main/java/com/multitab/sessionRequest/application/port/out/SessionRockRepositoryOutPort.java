package com.multitab.sessionRequest.application.port.out;

public interface SessionRockRepositoryOutPort {
    Boolean sessionRock(String sessionUuid);

    Boolean sessionUnRock(String sessionUuid);

}
