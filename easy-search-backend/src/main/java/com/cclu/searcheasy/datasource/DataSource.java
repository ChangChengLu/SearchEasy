package com.cclu.searcheasy.datasource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author ChangCheng Lu
 * @date 2023/7/18 9:04
 */
public interface DataSource<T> {

    /**
     * 搜索
     * @param searchText
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<T> doSearch(String searchText, long pageNum, long pageSize);

}
