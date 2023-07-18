package com.cclu.searcheasy.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cclu.searcheasy.model.dto.picture.PictureQueryRequest;
import com.cclu.searcheasy.model.entity.Picture;

/**
 * @author ChangCheng Lu
 * @date 2023/7/16 8:43
 */
public interface PictureService {

    Page<Picture> searchPicture(String searchText, long pageNum, long pageSize);

    Page<Picture> listPictureByPage(PictureQueryRequest pictureQueryRequest);

}
