package com.learn.mongodhelp.base;


import com.learn.mongodhelp.domian.MPageData;

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
