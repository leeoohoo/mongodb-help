package com.learn.mongod.base;

/**
 * 修改
 * email leeoohoo@gmail.com
 * mongod-help
 * Created by lee on 2019-07-01.
 */

public class UpdateQuery {

    private MongoQuery mq;

    public UpdateQuery(MongoQuery mq) {
        this.mq = mq;
    }

    public UpdateQuery set(String key, Object value) {
        this.mq.setUpdate(this.mq.getUpdate().set(key,value));
        return this;
    }

    public WhereQuery where() {
        return new WhereQuery(this.mq);
    }

}
