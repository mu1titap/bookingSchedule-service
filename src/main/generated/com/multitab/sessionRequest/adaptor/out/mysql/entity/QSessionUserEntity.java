package com.multitab.sessionRequest.adaptor.out.mysql.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSessionUserEntity is a Querydsl query type for SessionUserEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSessionUserEntity extends EntityPathBase<SessionUserEntity> {

    private static final long serialVersionUID = 556068766L;

    public static final QSessionUserEntity sessionUserEntity = new QSessionUserEntity("sessionUserEntity");

    public final com.multitab.sessionRequest.common.entity.QBaseEntity _super = new com.multitab.sessionRequest.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath menteeUuid = createString("menteeUuid");

    public final StringPath sessionUuid = createString("sessionUuid");

    public final EnumPath<com.multitab.sessionRequest.domain.Status> status = createEnum("status", com.multitab.sessionRequest.domain.Status.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QSessionUserEntity(String variable) {
        super(SessionUserEntity.class, forVariable(variable));
    }

    public QSessionUserEntity(Path<? extends SessionUserEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSessionUserEntity(PathMetadata metadata) {
        super(SessionUserEntity.class, metadata);
    }

}

