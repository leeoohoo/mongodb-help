/*
 * @Description:
 * @version:
 * @Author: leeoohoo
 * @Date: DO not edit
 * @LastEditors: leeoohoo
 * @LastEditTime: 2020-04-26 13:54:55
 */
package com.learn.mongod.utils;

import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.*;

@Slf4j
public class ClassUtils {

    /**
     * description: 该方法用于传入某实例对象以及对象方法名、修改值，通过放射调用该对象的某个set方法设置修改值
     *
     * @param beanObj  beanObj
     * @param property property
     * @param value    value
     * @return {@link Object}
     */
    public static <T> Object setProperty(T beanObj, String property, Object value) {
        try {
            if (beanObj == null) {
                throw new RuntimeException("beanObj为空");
            } else if (property == null || property.equals("")) {
                throw new RuntimeException("property为空");
            } else {
                PropertyDescriptor pd = new PropertyDescriptor(property, beanObj.getClass());
                Method setMethod = pd.getWriteMethod();
                if (setMethod == null) {
                    throw new RuntimeException(beanObj.getClass().getName() + "没有实体" + property);
                }
                return setMethod.invoke(beanObj, value);
            }
        } catch (Exception e) {
            throw new SetPropertyException(e.getMessage());
        }
    }

    /**
     * description: 该方法用于传入某实例对象以及对象方法名、修改值，通过放射调用该对象的某个set方法设置修改值
     *
     * @param beanObj  beanObj
     * @param property property
     * @param value    value
     * @param clazz    clazz
     * @return {@link Object}
     */
    public static <T> Object setProperty(T beanObj, String property, Object value, Class clazz) {
        try {
            if (beanObj == null) {
                throw new RuntimeException("beanObj为空");
            } else if (property == null || property.equals("")) {
                throw new RuntimeException("property为空");
            } else {
                PropertyDescriptor pd = new PropertyDescriptor(property, beanObj.getClass());
                Method setMethod = pd.getWriteMethod();
                if (setMethod == null) {
                    throw new RuntimeException(beanObj.getClass().getName() + "没有实体" + property);
                }
                if (clazz.getName().contains("Long")) {
                    value = Long.parseLong(value.toString());
                } else if (clazz.getName().contains("BigInteger")) {
                    value = BigInteger.valueOf((Long) value);
                }
                return setMethod.invoke(beanObj, value);
            }
        } catch (Exception e) {
            throw new GetPropertyException(e.getMessage());
        }
    }

    /**
     * description: 该方法用于传入某实例对象以及对象方法名，通过反射调用该对象的某个get方法
     *
     * @param beanObj  beanObj
     * @param property property
     * @return {@link Object}
     */
    public static <T> Object getProperty(T beanObj, String property) {
        try {
            if (beanObj == null) {
                throw new RuntimeException("beanObj为空");
            } else if (property == null || property.equals("")) {
                throw new RuntimeException("property为空");
            } else {
                PropertyDescriptor pd = new PropertyDescriptor(property, beanObj.getClass());
                Method getMethod = pd.getReadMethod();
                if (getMethod == null) {
                    throw new RuntimeException(beanObj.getClass().getName() + "没有实体" + property);
                }
                return getMethod.invoke(beanObj);
            }
        } catch (Exception e) {
            throw new GetPropertyException(e.getMessage());
        }
    }

    /**
     * description: 判断列是否存在
     *
     * @param beanObj  beanObj
     * @param property property
     * @return {@link boolean}
     */
    public static <T> boolean hasColumn(T beanObj, String property) {
        try {
            PropertyDescriptor pd = new PropertyDescriptor(property, beanObj.getClass());
            Method getMethod = pd.getReadMethod();
            if (getMethod == null) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * description: 将对象中不为null的字断转换为map
     *
     * @param t t
     * @return {@link Map<String, Object>}
     */
    public static Map<String, Object> tToMap(Object t) {
        Map<String, Object> map = new HashMap<>();
        Field[] childFields = t.getClass().getDeclaredFields();
        Field[] superTClassFields = t.getClass().getSuperclass().getDeclaredFields();
        List<String> fields = getFildsName(childFields, superTClassFields);
        for (String filed : fields) {
            Object value = ClassUtils.getProperty(t, filed);
            if (value != null) {
                map.put(filed, value);
            }
        }
        return map;
    }

    /**
     * description:
     *
     * @param fields fields
     * @return {@link List< String>}
     */
    public static List<String> getFildsName(Field[]... fields) {
        List<String> fildsName = new ArrayList<>();
        if (fields.length <= 0) {
            return fildsName;
        }
        List<Field[]> fieldss = Arrays.asList(fields);
        fieldss.forEach(fs -> {
            Arrays.asList(fs).forEach(f -> fildsName.add(f.getName()));
        });
        return fildsName;
    }


}
