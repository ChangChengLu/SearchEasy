package com.cclu.searcheasy.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cclu.searcheasy.common.BaseResponse;
import com.cclu.searcheasy.common.ResultUtils;
import com.cclu.searcheasy.model.dto.picture.PictureQueryRequest;
import com.cclu.searcheasy.model.entity.Picture;
import com.cclu.searcheasy.service.PictureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author ChangCheng Lu
 * @date 2023/7/16 9:13
 */
@RestController
@RequestMapping("/picture")
@Slf4j
public class PictureController {

    @Resource
    private PictureService pictureService;

    @PostMapping("/list/page/vo")
    public BaseResponse<Page<Picture>> listPictureByPage(@RequestBody PictureQueryRequest pictureQueryRequest, HttpServletRequest request) {
        String searchText = pictureQueryRequest.getSearchText();
        long pageNum = pictureQueryRequest.getCurrent();

        Page<Picture> picturePage = pictureService.searchPicture(searchText, pageNum, 10);
        return ResultUtils.success(picturePage);
    }
}
