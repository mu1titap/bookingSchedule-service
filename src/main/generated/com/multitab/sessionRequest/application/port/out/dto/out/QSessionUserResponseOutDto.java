package com.multitab.sessionRequest.application.port.out.dto.out;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.multitab.sessionRequest.application.port.out.dto.out.QSessionUserResponseOutDto is a Querydsl Projection type for SessionUserResponseOutDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QSessionUserResponseOutDto extends ConstructorExpression<SessionUserResponseOutDto> {

    private static final long serialVersionUID = -1895365243L;

    public QSessionUserResponseOutDto(com.querydsl.core.types.Expression<String> id, com.querydsl.core.types.Expression<String> sessionUuid, com.querydsl.core.types.Expression<String> menteeUuid, com.querydsl.core.types.Expression<com.multitab.sessionRequest.domain.Status> status) {
        super(SessionUserResponseOutDto.class, new Class<?>[]{String.class, String.class, String.class, com.multitab.sessionRequest.domain.Status.class}, id, sessionUuid, menteeUuid, status);
    }

}

