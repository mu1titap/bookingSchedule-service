package com.multitab.sessionRequest.adaptor.out.mysql.repository;

import com.multitab.sessionRequest.adaptor.out.mysql.entity.QSessionUserEntity;
import com.multitab.sessionRequest.adaptor.out.mysql.entity.SessionUserEntity;
import com.multitab.sessionRequest.application.port.out.dto.out.QSessionUserResponseOutDto;
import com.multitab.sessionRequest.application.port.out.dto.out.SessionUserResponseOutDto;
import com.multitab.sessionRequest.domain.Status;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.multitab.sessionRequest.adaptor.out.mysql.entity.QSessionUserEntity.sessionUserEntity;

@Repository
@RequiredArgsConstructor
public class SessionUserDslRepositoryImpl implements SessionUserDslRepository{
    private final JPAQueryFactory queryFactory;
    @Override
    public List<SessionUserResponseOutDto> getPendingSessionUser(String sessionUuid) {
        return queryFactory.select(
                    new QSessionUserResponseOutDto(sessionUserEntity.id.stringValue(), sessionUserEntity.sessionUuid,
                            sessionUserEntity.menteeUuid, sessionUserEntity.status)
                )
                .from(sessionUserEntity)
                .where(sessionUserEntity.sessionUuid.eq(sessionUuid)
                .and(sessionUserEntity.status.eq(Status.PENDING)))
                .fetch();
    }

    @Override
    public List<SessionUserResponseOutDto> getConfirmedSessionUser(String sessionUuid) {
        return queryFactory.select(
                        new QSessionUserResponseOutDto(sessionUserEntity.id.stringValue(), sessionUserEntity.sessionUuid,
                                sessionUserEntity.menteeUuid, sessionUserEntity.status)
                )
                .from(sessionUserEntity)
                .where(sessionUserEntity.sessionUuid.eq(sessionUuid)
                        .and(sessionUserEntity.status.eq(Status.CONFIRMED)))
                .fetch();
    }

    @Override
    public void deadlineUpdateSessionUserStatus(List<String> sessionUserIdList, boolean sessionIsConfirmed) {
        List<Long> sessionUserIdListLong = sessionUserIdList.stream().map(Long::valueOf).toList();
        queryFactory.update(sessionUserEntity)
                .set(sessionUserEntity.status, sessionIsConfirmed ? Status.CONFIRMED : Status.CANCELLED_BY_SYSTEM)
                .where(sessionUserEntity.id.in(sessionUserIdListLong))
                .execute();
    }

    @Override
    public boolean checkSessionUserValidityStatus(String sessionUuid, String userUuid) {
         Integer fetchOne = queryFactory
                            .selectOne()
                            .from(sessionUserEntity)
                            .where(
                                    sessionUserEntity.sessionUuid.eq(sessionUuid)
                                            .and(sessionUserEntity.menteeUuid.eq(userUuid))
                                            .and(sessionUserEntity.status.eq(Status.CONFIRMED))
                            )
                            .fetchFirst();

        return fetchOne != null;
    }

    @Override
    public void updateStatusEndSessionUser(List<String> sessionUserIdList ) {
        List<Long> sessionUserIdListLong = sessionUserIdList.stream().map(Long::valueOf).toList();
        queryFactory.update(sessionUserEntity)
                .set(sessionUserEntity.status, Status.END )
                .where(sessionUserEntity.id.in(sessionUserIdListLong))
                .execute();
    }
}
