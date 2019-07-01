package com.learn.mongodhelp.base;

import com.learn.mongodhelp.utils.SpringUtil;
import lombok.Data;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;


/**
 * mongodb 的工具类
 * leeoohoo@gmail.com
 * @param <T>
 */
@Component
public class LMongo {


    private static MongoQuery getMq() {
        var mq = new MongoQuery();
        mq.setMongoTemplate((MongoTemplate) SpringUtil.getBean("mongoTemplate"));
        return mq;
    }

    public static  <T> boolean save(T t) {
        getMq().getMongoTemplate().save(t);
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
