/*
 * @Description: 
 * @version: 
 * @Author: leeoohoo
 * @Date: 2020-04-24 16:27:35
 * @LastEditors: leeoohoo
 * @LastEditTime: 2020-04-24 16:28:47
 */
package com.learn.mongod.base;

import com.learn.mongod.utils.SpringUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.QueryBuilder;
import lombok.Data;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;


/**
 * mongodb 的工具类
 * leeoohoo@gmail.com
 */
@Data
@SuppressWarnings({"unused", "unchecked", "rawtypes", "null", "hiding"})
public class LMongo {


    private static MongoQuery getMq() {
        MongoQuery mq = new MongoQuery();
        mq.setQueryBuilder(new QueryBuilder());
        mq.setFieldsObject(new BasicDBObject());
        mq.setUpdate(new Update());
        mq.setMongoTemplate((MongoTemplate) SpringUtil.getBean("mongoTemplate"));
        return mq;
    }

    public static <T> boolean save(T t) {
        getMq().getMongoTemplate().save(t);
        return true;
    }

    public static <T> boolean save(List<T> ts) {
        MongoTemplate mongoTemplate = getMq().getMongoTemplate();
        ts.forEach(
               t -> mongoTemplate.save(t)
        );
        return true;
    }

    public static <T> UpdateQuery update(Class<T> tClass) {
        var mq = getMq();
        mq.setTClass(tClass);
        return new UpdateQuery(mq);
    }

    public static  <T>  Long delete(T t) {
        var result =  getMq().getMongoTemplate().remove(t);
        return result.getDeletedCount();
    }

    public static synchronized  <T> SelectQuery find(Class<T> tClass) {
        var mq = getMq();
        mq.setTClass(tClass);
        return new SelectQuery(mq);
    }

    public static synchronized <T> DeleteQuery delete(Class<T> tClass) {
        var mq = getMq();
        mq.setTClass(tClass);
        return new DeleteQuery(mq);
    }








}
