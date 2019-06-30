package com.learn.mongodhelp.base;

import com.learn.mongodhelp.domian.PageData;
import com.mongodb.BasicDBObject;
import com.mongodb.QueryBuilder;
import lombok.Data;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

@Data
public class MongoQuery<T> {
    private MongoTemplate mongoTemplate;

    private QueryBuilder queryBuilder;

    private Pageable pageable;

    private Sort sort;

    private Query query;

    private BasicDBObject fieldsObject;

    private Class<T> tClass;

    private PageData pageData;
}
