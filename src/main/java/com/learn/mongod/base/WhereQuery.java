package com.learn.mongod.base;

import com.mongodb.BasicDBObject;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.mongodb.core.query.BasicQuery;

import java.util.List;
import java.util.regex.Pattern;

public class WhereQuery<T>{
    private MongoQuery<T> mq;
    public WhereQuery (MongoQuery mongoQuery) {
        this.mq = mongoQuery;
    }




    public Long delete() {
        this.where();
        initQuery();
        DeleteResult remove = this.mq.getMongoTemplate().remove(this.mq.getQuery(), this.mq.getTClass());
        return remove.getDeletedCount();
    }

    private void initQuery () {
        if(null == this.mq.getTClass() || null == this.mq.getQueryBuilder()) {
            throw new RuntimeException("缺少条件");
        }else {
            this.mq.setQuery(new BasicQuery(this.mq.getQueryBuilder().get().toString()));
        }
    }


    public List<T> findList() {
        this.where();
        initSelect();
        this.mq.setQuery(new BasicQuery(this.mq.getQueryBuilder().get().toString(), this.mq.getFieldsObject().toJson()));
        return mq.getMongoTemplate().find(this.mq.getQuery(), this.mq.getTClass());
    }

    public Page<T> findPage() {
        this.where();
        if (null == this.mq.getPageable()) {
            throw new RuntimeException("请先设置分页");
        }
        this.mq.setQuery(new BasicQuery(this.mq.getQueryBuilder().get().toString(), this.mq.getFieldsObject().toJson()));
        var list = this.mq.getMongoTemplate().find(this.mq.getQuery().with(this.mq.getPageable()),this.mq.getTClass());
        long total = this.mq.getMongoTemplate().count(this.mq.getQuery(), this.mq.getTClass());
        Page<T> page = new PageImpl<>(list, this.mq.getPageable(), total);
        return page;
    }

    public T findOne() {
        this.where();
        this.initSelect();
        this.mq.setQuery(new BasicQuery(this.mq.getQueryBuilder().get().toString(), this.mq.getFieldsObject().toJson()));
        return this.mq.getMongoTemplate().findOne(this.mq.getQuery(), this.mq.getTClass());
    }


    /**
     * updateFirst 更新查询返回结果集的第一条
     * @return 修改条数
     */
    public Long updateFirst () {
        this.where();
        initQuery();
        UpdateResult updateResult = this.mq.getMongoTemplate().updateFirst(this.mq.getQuery(), this.mq.getUpdate(), this.mq.getTClass());
        return updateResult.getModifiedCount();
    }

    /**
     * updateMulti 更新查询返回结果集的全部
     * @return 修改条数
     */
    public Long updateMulti () {
        this.where();
        initQuery();
        UpdateResult updateResult = this.mq.getMongoTemplate().updateMulti(this.mq.getQuery(), this.mq.getUpdate(), this.mq.getTClass());
        return updateResult.getModifiedCount();
    }

    /**
     * upsert 更新对象不存在则去添加
     * @return 修改条数
     */
    public Long upsert () {
        this.where();
        initQuery();
        UpdateResult updateResult = this.mq.getMongoTemplate().upsert(this.mq.getQuery(), this.mq.getUpdate(), this.mq.getTClass());
        return updateResult.getModifiedCount();
    }

    private void initSelect() {
        if (null == this.mq.getFieldsObject()) {
            this.mq.setFieldsObject(new BasicDBObject());
        }
    }


    public WhereQuery or(BasicDBObject... basicDBObjects) {
        this.mq.getQueryBuilder().or(basicDBObjects);
        return this;
    }


    public WhereQuery eq(String key, Object o) {
        this.mq.getQueryBuilder().and(key).is(o);
        return this;
    }

    public WhereQuery like(String key, String o) {
        Pattern pattern = Pattern.compile("^.*" + o + ".*$", Pattern.CASE_INSENSITIVE);
        this.mq.getQueryBuilder().and(key).regex(pattern);
        return this;
    }

    public WhereQuery gt(String key, Object o) {
        this.mq.getQueryBuilder().and(key).greaterThan(o);
        return this;
    }

    public WhereQuery ge(String key, Object o) {
        this.mq.getQueryBuilder().and(key).greaterThanEquals(o);
        return this;
    }

    public WhereQuery lt(String key, Object o) {
        this.mq.getQueryBuilder().and(key).lessThan(o);
        return this;
    }

    public WhereQuery le(String key, Object o) {
        this.mq.getQueryBuilder().and(key).is(o);
        return this;
    }

    public WhereQuery in(String key, List o) {
        this.mq.getQueryBuilder().and(key).in(o);
        return this;
    }

    public WhereQuery notIn(String key, List o) {
        this.mq.getQueryBuilder().and(key).notIn(o);
        return this;
    }

    private WhereQuery where() {
        if (this.mq.getPageData() == null) {
            return this;
        }
        this.mq.getPageData().getMap().forEach(
                (k, v) -> {
                    var stes = k.split("_");
                    if (stes.length > 1) {
                        switch (stes[1]) {
                            case "like":
                                var value = v.toString();
                                if (value != null && !"".equals(value.trim())) {
                                    this.like(stes[0], value);
                                }
                                break;
                            case "eq":
                                if (v != null) {
                                    this.eq(stes[0],v);
                                }
                                break;
                            case "gt":
                                if (v != null) {
                                    this.gt(stes[0],v);
                                }
                                break;
                            case "ge":
                                if (v != null) {
                                    this.ge(stes[0],v);
                                }
                                break;
                            case "le":
                                if (v != null) {
                                    this.le(stes[0],v);
                                }
                                break;
                            case "lt":
                                if (v != null) {
                                    this.lt(stes[0],v);
                                }
                                break;
                            case "in":
                                if (v != null) {
                                    var list = (List) v;
                                    if (list.size() > 0) {
                                        this.in(stes[0],list);
                                    }
                                }

                                break;
                            case "notIn":
                                if (v != null) {
                                    var list = (List) v;
                                    if (list.size() > 0) {
                                        this.notIn(stes[0],list);
                                    }
                                }
                                break;

                        }
                    }

                }
        );
        return this;
    }

}
