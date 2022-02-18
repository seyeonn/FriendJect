package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSolutionBook is a Querydsl query type for SolutionBook
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSolutionBook extends EntityPathBase<SolutionBook> {

    private static final long serialVersionUID = -1770520380L;

    public static final QSolutionBook solutionBook = new QSolutionBook("solutionBook");

    public final NumberPath<Integer> no = createNumber("no", Integer.class);

    public final StringPath text = createString("text");

    public QSolutionBook(String variable) {
        super(SolutionBook.class, forVariable(variable));
    }

    public QSolutionBook(Path<? extends SolutionBook> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSolutionBook(PathMetadata metadata) {
        super(SolutionBook.class, metadata);
    }

}

