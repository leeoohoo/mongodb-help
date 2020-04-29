/*
 * @Description: 
 * @version: 
 * @Author: leeoohoo
 * @Date: 2020-04-26 10:28:22
 * @LastEditors: leeoohoo
 * @LastEditTime: 2020-04-29 16:41:09
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
        long pageCount = total/rows;
        MyPager pager = MyPager.builder()
            .pageCount(total%rows==0 ? pageCount : pageCount + 1)
            .recordCount(total)
            .pageNumber(pageIndex)
            .pageSize(rows)
            .build();
        this.pager = pager;
    }
}
