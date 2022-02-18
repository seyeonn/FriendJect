package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QStudyroom is a Querydsl query type for Studyroom
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudyroom extends EntityPathBase<Studyroom> {

    private static final long serialVersionUID = -1582391134L;

    public static final QStudyroom studyroom = new QStudyroom("studyroom");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> time = createNumber("time", Integer.class);

    public final StringPath userid = createString("userid");

    public QStudyroom(String variable) {
        super(Studyroom.class, forVariable(variable));
    }

    public QStudyroom(Path<? extends Studyroom> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStudyroom(PathMetadata metadata) {
        super(Studyroom.class, metadata);
    }

}

