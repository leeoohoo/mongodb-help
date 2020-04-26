/*
 * @Description: 
 * @version: 
 * @Author: leeoohoo
 * @Date: 2020-04-24 16:27:35
 * @LastEditors: leeoohoo
 * @LastEditTime: 2020-04-26 14:04:05
 */
package com.learn.mongod.base;

import java.lang.reflect.Field;

import com.learn.mongod.utils.ClassUtils;

/**
 * 修改 email leeoohoo@gmail.com mongod-help Created by lee on 2019-07-01.
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


    public UpdateQuery<T> set(T t) {
        try {
            Field[] fields = t.getClass().getDeclaredFields();
            Field[] supperFields = t.getClass().getSuperclass().getDeclaredFields();
            for(Field field : fields) {
                if("serialVersionUID".equals(field.getName())) {
                    continue;
                }
                set(field.getName(),ClassUtils.getProperty(t, field.getName()));
            }
            for(Field field : supperFields) {
                if("serialVersionUID".equals(field.getName())) {
                    continue;
                }
                set(field.getName(),ClassUtils.getProperty(t, field.getName()));
            }

        } catch (Exception e) {
            throw new RuntimeException("初始化查询字段出现异常，请检查dto实体类是否正常");
        }
        return this;
    }

    public WhereQuery<T> where() {
        return new WhereQuery<T>(this.mq);
    }

}
