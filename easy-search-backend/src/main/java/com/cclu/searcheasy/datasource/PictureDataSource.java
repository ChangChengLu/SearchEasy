package com.cclu.searcheasy.datasource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cclu.searcheasy.model.dto.picture.PictureQueryRequest;
import com.cclu.searcheasy.model.entity.Picture;
import com.cclu.searcheasy.service.PictureService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ChangCheng Lu
 * @date 2023/7/18 9:14
 */
@Service
public class PictureDataSource implements DataSource<Picture> {

    @Resource
    private PictureService pictureService;

    @Override
    public Page<Picture> doSearch(String searchText, long pageNum, long pageSize) {
        PictureQueryRequest pictureQueryRequest = new PictureQueryRequest();
        pictureQueryRequest.setSearchText(searchText);
        pictureQueryRequest.setCurrent(pageNum);
        pictureQueryRequest.setPageSize(pageSize);

        return pictureService.listPictureByPage(pictureQueryRequest);
    }
}
