package com.learn.mongodhelp.service;

import com.learn.mongodhelp.base.LMongo;
import com.learn.mongodhelp.domian.PageData;
import com.learn.mongodhelp.entity.Role;
import com.learn.mongodhelp.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * mongodb测试类
 * email leeoohoo@gmail.com
 * mongod-help
 * Created by lee on 2019-07-01.
 */
@Service
public class TestService {

    /**
     * 保存
     * @return 保存后的结果
     */
    public Object save() {
        List<Role> roles = new ArrayList<>();
        Role role = new Role(1L,"ddff");
        Role role1 = new Role(2L,"fdsfds");
        Role role2 = new Role(1L,"ddff");
        roles.add(role);
        roles.add(role1);
        roles.add(role2);
        User user = new User(1L,"fff",2,roles);
        var result = LMongo.save(user);
        return result;
    }

    /**
     * 删除
     * @return 删除后的结果
     */
    public Object delete() {
        var result = LMongo
                .delete(User.class)
                .where()
                .eq("id",1L)
                .delete();

        User user = new User(1L,"ddd",2);
        result = LMongo.delete(user);
        return result;
    }

    /**
     * 修改
     * @return 修改的结果
     */
    public Object update() {
        var result = LMongo.update(User.class)
                .set("id", 1L)
                .set("name", "gggg")
                .where()
                .eq("id", 1L)
                .updateFirst();
        return result;
    }

    /**
     * 查找
     * @return 查找的结果
     */
    public Object find() {
        Object user = LMongo
                .find(User.class)
                .select("roles")
                .where(new PageData())
                .eq("id", 1L)
                .findList();
        return user;
    }


}
