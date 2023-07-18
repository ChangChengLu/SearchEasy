package com.cclu.searcheasy.manage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cclu.searcheasy.datasource.*;
import com.cclu.searcheasy.model.dto.search.SearchRequest;
import com.cclu.searcheasy.model.entity.Picture;
import com.cclu.searcheasy.model.enums.SearchTypeEnum;
import com.cclu.searcheasy.model.vo.PostVO;
import com.cclu.searcheasy.model.vo.SearchVO;
import com.cclu.searcheasy.model.vo.UserVO;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author ChangCheng Lu
 * @date 2023/7/18 8:57
 */
@Component
public class SearchFacade {

    @Resource
    private PostDataSource postDataSource;

    @Resource
    private UserDataSource userDataSource;

    @Resource
    private PictureDataSource pictureDataSource;

    @Resource
    private DataSourceRegistry dataSourceRegistry;


    public SearchVO doSearchByType(SearchRequest searchRequest) {
        String searchText = searchRequest.getSearchText();
        String type = searchRequest.getType();
        long pageNum = searchRequest.getCurrent();
        long pageSize = searchRequest.getPageSize();

        SearchTypeEnum searchTypeEnum = SearchTypeEnum.getSearchTypeByValue(type);

        if (searchTypeEnum == null) {
            return doSearchAll(searchRequest);
        } else {
            DataSource<T> dataSource = dataSourceRegistry.getDataSourceByType(type);

            SearchVO searchVO = new SearchVO();
            Page<T> page = dataSource.doSearch(searchText, pageNum, pageSize);
            searchVO.setDataList(page.getRecords());
            return searchVO;
        }
    }

    public SearchVO doSearchAll(SearchRequest searchRequest) {
        String searchText = searchRequest.getSearchText();
        long pageNum = searchRequest.getCurrent();
        long pageSize = searchRequest.getPageSize();

        Page<PostVO> postVOPage = postDataSource.doSearch(searchText, pageNum, pageSize);
        Page<UserVO> userVOPage = userDataSource.doSearch(searchText, pageNum, pageSize);
        Page<Picture> picturePage = pictureDataSource.doSearch(searchText, pageNum, pageSize);

        SearchVO searchVO = new SearchVO();
        searchVO.setPostList(postVOPage.getRecords());
        searchVO.setUserList(userVOPage.getRecords());
        searchVO.setPictureList(picturePage.getRecords());

        return searchVO;
    }

}
