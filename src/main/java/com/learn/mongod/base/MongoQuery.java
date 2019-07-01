package com.learn.mongod.base;

import com.learn.mongod.domian.MPageData;
import com.mongodb.BasicDBObject;
import com.mongodb.QueryBuilder;
import lombok.Data;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@Data
public class MongoQuery<T> {
    private MongoTemplate mongoTemplate;

    private QueryBuilder queryBuilder = new QueryBuilder();

    private Pageable pageable ;

    private Sort sort;

    private Query query;

    private BasicDBObject fieldsObject = new BasicDBObject();

    private Class<T> tClass;

    private MPageData pageData;

    private Update update = new Update();
}
