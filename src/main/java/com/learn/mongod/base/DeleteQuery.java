/*
 * @Description: 
 * @version: 
 * @Author: leeoohoo
 * @Date: 2020-04-24 16:27:35
 * @LastEditors: leeoohoo
 * @LastEditTime: 2020-04-26 09:29:38
 */
package com.learn.mongod.base;


import com.learn.mongod.domian.MPageData;

public class DeleteQuery<T> {

    protected MongoQuery<T> mq;
    
    public DeleteQuery (MongoQuery<T> mongoQuery) {
        this.mq = mongoQuery;
    }


    public WhereQuery<T> where() {
        return new WhereQuery<T>(this.mq);
    }

    public WhereQuery<T> where(MPageData pageData) {
        this.mq.setPageData(pageData);
        return new WhereQuery<T>(this.mq);
    }

}
