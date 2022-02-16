package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QVotLog is a Querydsl query type for VotLog
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QVotLog extends EntityPathBase<VotLog> {

    private static final long serialVersionUID = 1803850411L;

    public static final QVotLog votLog = new QVotLog("votLog");

    public final StringPath date = createString("date");

    public final StringPath result = createString("result");

    public final StringPath title = createString("title");

    public final NumberPath<Integer> userId = createNumber("userId", Integer.class);

    public QVotLog(String variable) {
        super(VotLog.class, forVariable(variable));
    }

    public QVotLog(Path<? extends VotLog> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVotLog(PathMetadata metadata) {
        super(VotLog.class, metadata);
    }

}

