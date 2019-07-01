package com.learn.mongod.base;


import com.learn.mongod.domian.MPageData;

public class DeleteQuery<T> {

    protected MongoQuery<T> mq;
    
    public DeleteQuery (MongoQuery mongoQuery) {
        this.mq = mongoQuery;
    }


    public WhereQuery where() {
        return new WhereQuery(this.mq);
    }

    public WhereQuery where(MPageData pageData) {
        this.mq.setPageData(pageData);
        return new WhereQuery(this.mq);
    }

}
