package com.multitab.sessionRequest.adaptor.out.mysql.repository;

import com.multitab.sessionRequest.adaptor.out.mysql.entity.SessionUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionUserJpaRepository extends JpaRepository<SessionUserEntity,Long> {

    List<SessionUserEntity> findAllBySessionUuid(String sessionUuid);
}
