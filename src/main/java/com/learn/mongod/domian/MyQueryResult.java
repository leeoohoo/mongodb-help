/*
 * @Description: 
 * @version: 
 * @Author: leeoohoo
 * @Date: 2020-04-26 10:28:22
 * @LastEditors: leeoohoo
 * @LastEditTime: 2020-04-26 10:38:41
 */
package com.learn.mongod.domian;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyQueryResult<T> {

    private List<T> list;

    private MyPager pager;


    public MyQueryResult(List<T> list,MPageData mPageData, Long total) {
        this.list = list;
        Integer pageIndex = mPageData.getPageIndex();
        Integer rows = mPageData.getRows();
        MyPager pager = MyPager.builder()
            .pageCount(total/rows)
            .recordCount(total)
            .pageNumber(pageIndex)
            .pageSize(rows)
            .build();
        this.pager = pager;
    }
}
