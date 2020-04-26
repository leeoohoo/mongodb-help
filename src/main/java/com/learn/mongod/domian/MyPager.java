/*
 * @Description: 
 * @version: 
 * @Author: leeoohoo
 * @Date: 2020-04-26 10:28:22
 * @LastEditors: leeoohoo
 * @LastEditTime: 2020-04-26 10:38:01
 */
package com.learn.mongod.domian;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyPager {

    private Integer pageSize;

    private Integer pageNumber;

    private Long pageCount;

    private Long recordCount;
}
