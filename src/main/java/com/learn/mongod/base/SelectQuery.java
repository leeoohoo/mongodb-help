/*
 * @Description: 
 * @version: 
 * @Author: leeoohoo
 * @Date: 2020-04-24 16:27:35
 * @LastEditors: leeoohoo
 * @LastEditTime: 2020-04-26 09:27:17
 */
package com.learn.mongod.base;

import com.learn.mongod.domian.MPageData;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;

@Data
public class SelectQuery<T> {

    private MongoQuery<T> mq;

    private MongoQuery<T> getMq() {
        return this.mq;
    }

    
    public SelectQuery(MongoQuery<T> mongoQuery) {
        this.mq = mongoQuery;
    }


    public SelectQuery<T> order(Sort.Direction sd, String fileds) {
        this.mq.setSort(Sort.by(sd, fileds));
        if (null != this.mq.getPageData()) {
            this.mq.setPageable(
                    PageRequest.of(
                            this.mq.getPageData().getPageIndex(),
                            this.mq.getPageData().getRows(),
                            this.mq.getSort())
            );
        }
        return this;
    }

    public SelectQuery<T> page(MPageData pageData) {
        this.mq.setPageData(pageData);
        if (null != this.mq.getSort()) {
            this.mq.setPageable(
                    PageRequest.of(
                            this.mq.getPageData().getPageIndex(),
                            this.mq.getPageData().getRows(),
                            this.mq.getSort()
                    )
            );
        } else {
            this.mq.setPageable(
                    PageRequest.of(
                            this.mq.getPageData().getPageIndex(),
                            this.mq.getPageData().getRows()
                            )
            );
        }
        return this;
    }


    public SelectQuery<T> select(String fileds) {
        String[] strings = null;
        if (fileds != null && !"".equals(fileds)) {
            strings = fileds.split(",");
        }
        Arrays.asList(strings).forEach(
                f -> {
                    this.mq.setFieldsObject(this.mq.getFieldsObject().append(f, 1));
                }
        );
        return this;
    }



    public WhereQuery<T> where(MPageData pageData) {
        this.mq.setPageData(pageData);
        return new WhereQuery<T>(this.mq);
    }


    public WhereQuery<T> where() {
        return new WhereQuery<T>(this.mq);
    }

}
