/*
 * @Description: 
 * @version: 
 * @Author: leeoohoo
 * @Date: 2020-04-24 16:27:35
 * @LastEditors: leeoohoo
 * @LastEditTime: 2020-04-26 15:34:55
 */
package com.learn.mongod.base;

import com.learn.mongod.utils.SpringUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.QueryBuilder;
import com.mongodb.client.result.DeleteResult;

import lombok.Data;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;


/**
 * mongodb 的工具类
 * leeoohoo@gmail.com
 */
@Data
public class LMongo {


    private static <T> MongoQuery<T> getMq() {
        MongoQuery<T> mq = new MongoQuery<T>();
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

    public static <T> UpdateQuery<T> update(Class<T> tClass) {
        MongoQuery<T> mq = getMq();
        mq.setTClass(tClass);
        return new UpdateQuery<T>(mq);
    }

    public static  <T>  Long delete(T t) {
        DeleteResult result =  getMq().getMongoTemplate().remove(t);
        return result.getDeletedCount();
    }

    public static <T> SelectQuery<T> find(Class<T> tClass) {
        MongoQuery<T> mq = getMq();
        mq.setTClass(tClass);
        return new SelectQuery<T>(mq);
    }

    public static  <T> DeleteQuery<T> delete(Class<T> tClass) {
        MongoQuery<T> mq = getMq();
        mq.setTClass(tClass);
        return new DeleteQuery<T>(mq);
    }








}
