package com.learn.mongod.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyOrganization {
    private String id;

    private String name;

    private Integer hasChild;

    private Integer type;

    private String parentId;
}
