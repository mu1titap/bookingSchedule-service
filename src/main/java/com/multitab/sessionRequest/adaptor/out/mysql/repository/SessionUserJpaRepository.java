package com.multitab.sessionRequest.adaptor.out.mysql.repository;

import com.multitab.sessionRequest.adaptor.out.mysql.entity.SessionUserEntity;
import com.multitab.sessionRequest.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SessionUserJpaRepository extends JpaRepository<SessionUserEntity,Long> {

    List<SessionUserEntity> findAllBySessionUuidAndStatus(String sessionUuid, Status status);

    Integer countBySessionUuidAndStatus(String sessionUuid, Status status);

    Optional<SessionUserEntity> findOneByMenteeUuidAndSessionUuid(String menteeUuid, String sessionUuid);

    @Query("UPDATE session_user su " +
            "SET su.status = :status " + "WHERE su.id = :sessionUserId")
    @Modifying
    @Transactional
    int updateSessionUser(@Param("status") Status status, @Param("sessionUserId") String sessionUserId);



}
