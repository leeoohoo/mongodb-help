/*
 * @Description: 
 * @version: 
 * @Author: leeoohoo
 * @Date: 2020-04-24 16:27:35
 * @LastEditors: leeoohoo
 * @LastEditTime: 2020-04-26 09:27:49
 */
package com.learn.mongod.base;

/**
 * 修改
 * email leeoohoo@gmail.com
 * mongod-help
 * Created by lee on 2019-07-01.
 */

public class UpdateQuery<T> {

    private MongoQuery<T> mq;

    public UpdateQuery(MongoQuery<T> mq) {
        this.mq = mq;
    }

    public UpdateQuery<T> set(String key, Object value) {
        this.mq.setUpdate(this.mq.getUpdate().set(key,value));
        return this;
    }

    public WhereQuery<T> where() {
        return new WhereQuery<T>(this.mq);
    }

}
