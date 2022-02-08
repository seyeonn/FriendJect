package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QConsultLog is a Querydsl query type for ConsultLog
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QConsultLog extends EntityPathBase<ConsultLog> {

    private static final long serialVersionUID = -1519957766L;

    public static final QConsultLog consultLog = new QConsultLog("consultLog");

    public final StringPath a_user_id = createString("a_user_id");

    public final StringPath answer = createString("answer");

    public final StringPath date = createString("date");

    public final NumberPath<Integer> no = createNumber("no", Integer.class);

    public final StringPath q_user_id = createString("q_user_id");

    public final StringPath question = createString("question");

    public QConsultLog(String variable) {
        super(ConsultLog.class, forVariable(variable));
    }

    public QConsultLog(Path<? extends ConsultLog> path) {
        super(path.getType(), path.getMetadata());
    }

    public QConsultLog(PathMetadata metadata) {
        super(ConsultLog.class, metadata);
    }

}

