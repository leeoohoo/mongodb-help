package com.learn.mongod.base;

import com.learn.mongod.domian.MyQueryResult;
import com.mongodb.BasicDBObject;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.mongodb.core.query.BasicQuery;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Slf4j
public class WhereQuery<T> {
    private MongoQuery<T> mq;

    public WhereQuery(MongoQuery<T> mongoQuery) {
        this.mq = mongoQuery;
    }

    public Long delete() {
        initQuery();
        DeleteResult remove = this.mq.getMongoTemplate().remove(this.mq.getQuery(), this.mq.getTClass());
        return remove.getDeletedCount();
    }

    private void initQuery() {
        if (null == this.mq.getTClass() || null == this.mq.getQueryBuilder()) {
            throw new RuntimeException("缺少条件");
        } else {
            this.mq.setQuery(new BasicQuery(this.mq.getQueryBuilder().get().toString()));
        }
    }

    public List<T> findList() {
        initSelect();
        this.mq.setQuery(
                new BasicQuery(this.mq.getQueryBuilder().get().toString(), this.mq.getFieldsObject().toJson()));
        return mq.getMongoTemplate().find(this.mq.getQuery(), this.mq.getTClass());
    }

    public <DTO> List<DTO> findList(Class<DTO> clazz) {
        initSelect();
        this.mq.setQuery(
                new BasicQuery(this.mq.getQueryBuilder().get().toString(), this.mq.getFieldsObject().toJson()));
        List<T> ts = mq.getMongoTemplate().find(this.mq.getQuery(), this.mq.getTClass());
        return convertDtos(ts, clazz);
    }

    private <DTO> List<DTO> convertDtos(List<T> list, Class<DTO> clazz) {
        List<DTO> dtos = new ArrayList<>();
        for (T t : list) {
            dtos.add(convertDto(t,clazz));
        }
        return dtos;
    }

    private <DTO> DTO convertDto(T t, Class<DTO> clazz) {
            try {
                DTO dto = clazz.newInstance();
                BeanUtils.copyProperties(t, dto);
                return dto;
            } catch (Exception e) {
                log.error("请检查实体类字段名与DTO字段名是否一致，tName:{},dtoName:{}", t.getClass().getName(),clazz.getName());
                throw new RuntimeException("请检查实体类字段名与DTO字段名是否一致");
            }
        
    }

    public <DTO> MyQueryResult<DTO> findPage(Class<DTO> clazz) {
        if (null == this.mq.getPageable()) {
            throw new RuntimeException("请先设置分页");
        }
        this.mq.setQuery(new BasicQuery(this.mq.getQueryBuilder().get().toString(), this.mq.getFieldsObject().toJson()));
        List<T> list = this.mq.getMongoTemplate().find(this.mq.getQuery().with(this.mq.getPageable()),this.mq.getTClass());
        List<DTO> dtos = convertDtos(list, clazz);
        long total = this.mq.getMongoTemplate().count(this.mq.getQuery(), this.mq.getTClass());
        MyQueryResult<DTO> myQueryResult = new MyQueryResult<DTO>(dtos,this.mq.getPageData(),total);
        return myQueryResult;
    }

    public MyQueryResult<T> findPage() {
        if (null == this.mq.getPageable()) {
            throw new RuntimeException("请先设置分页");
        }
        this.mq.setQuery(new BasicQuery(this.mq.getQueryBuilder().get().toString(), this.mq.getFieldsObject().toJson()));
        List<T> list = this.mq.getMongoTemplate().find(this.mq.getQuery().with(this.mq.getPageable()),this.mq.getTClass());
        long total = this.mq.getMongoTemplate().count(this.mq.getQuery(), this.mq.getTClass());
        MyQueryResult<T> myQueryResult = new MyQueryResult<T>(list,this.mq.getPageData(),total);
        return myQueryResult;
    }

    public T findOne() {
        this.initSelect();
        this.mq.setQuery(new BasicQuery(this.mq.getQueryBuilder().get().toString(), this.mq.getFieldsObject().toJson()));
        return this.mq.getMongoTemplate().findOne(this.mq.getQuery(), this.mq.getTClass());
    }
    public <DTO> DTO findOne(Class<DTO> clazz) {
        this.initSelect();
        this.mq.setQuery(new BasicQuery(this.mq.getQueryBuilder().get().toString(), this.mq.getFieldsObject().toJson()));
        T t = this.mq.getMongoTemplate().findOne(this.mq.getQuery(), this.mq.getTClass());
        return convertDto(t, clazz);
    }


    /**
     * updateFirst 更新查询返回结果集的第一条
     * @return 修改条数
     */
    public Long updateFirst () {
        initQuery();
        UpdateResult updateResult = this.mq.getMongoTemplate().updateFirst(this.mq.getQuery(), this.mq.getUpdate(), this.mq.getTClass());
        return updateResult.getModifiedCount();
    }

    /**
     * updateMulti 更新查询返回结果集的全部
     * @return 修改条数
     */
    public Long updateMulti () {
        initQuery();
        UpdateResult updateResult = this.mq.getMongoTemplate().updateMulti(this.mq.getQuery(), this.mq.getUpdate(), this.mq.getTClass());
        return updateResult.getModifiedCount();
    }

    /**
     * upsert 更新对象不存在则去添加
     * @return 修改条数
     */
    public Long upsert () {
        initQuery();
        UpdateResult updateResult = this.mq.getMongoTemplate().upsert(this.mq.getQuery(), this.mq.getUpdate(), this.mq.getTClass());
        return updateResult.getModifiedCount();
    }

    private void initSelect() {
        if (null == this.mq.getFieldsObject()) {
            this.mq.setFieldsObject(new BasicDBObject());
        }
    }


    public WhereQuery<T> or(BasicDBObject... basicDBObjects) {
        this.mq.getQueryBuilder().or(basicDBObjects);
        return this;
    }


    public WhereQuery<T> eq(String key, Object o) {
        this.mq.getQueryBuilder().and(key).is(o);
        return this;
    }

    public WhereQuery<T> like(String key, String o) {
        Pattern pattern = Pattern.compile("^.*" + o + ".*$", Pattern.CASE_INSENSITIVE);
        this.mq.getQueryBuilder().and(key).regex(pattern);
        return this;
    }

    public WhereQuery<T> gt(String key, Object o) {
        this.mq.getQueryBuilder().and(key).greaterThan(o);
        return this;
    }

    public WhereQuery<T> ge(String key, Object o) {
        this.mq.getQueryBuilder().and(key).greaterThanEquals(o);
        return this;
    }

    public WhereQuery<T> lt(String key, Object o) {
        this.mq.getQueryBuilder().and(key).lessThan(o);
        return this;
    }

    public WhereQuery<T> le(String key, Object o) {
        this.mq.getQueryBuilder().and(key).is(o);
        return this;
    }

    public WhereQuery<T> in(String key, List<?> o) {
        this.mq.getQueryBuilder().and(key).in(o);
        return this;
    }

    public WhereQuery<T> notIn(String key, List<?> o) {
        this.mq.getQueryBuilder().and(key).notIn(o);
        return this;
    }

    

}
