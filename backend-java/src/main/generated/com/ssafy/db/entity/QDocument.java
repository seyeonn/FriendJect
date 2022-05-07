package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDocument is a Querydsl query type for Document
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDocument extends EntityPathBase<Document> {

    private static final long serialVersionUID = 873720541L;

    public static final QDocument document = new QDocument("document");

    public final StringPath content = createString("content");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> meetingTime = createDateTime("meetingTime", java.time.LocalDateTime.class);

    public final StringPath title = createString("title");

    public QDocument(String variable) {
        super(Document.class, forVariable(variable));
    }

    public QDocument(Path<? extends Document> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDocument(PathMetadata metadata) {
        super(Document.class, metadata);
    }

}

