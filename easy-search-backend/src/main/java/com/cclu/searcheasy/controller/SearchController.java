package com.cclu.searcheasy.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cclu.searcheasy.common.BaseResponse;
import com.cclu.searcheasy.common.ResultUtils;
import com.cclu.searcheasy.manage.SearchFacade;
import com.cclu.searcheasy.model.dto.post.PostQueryRequest;
import com.cclu.searcheasy.model.dto.search.SearchRequest;
import com.cclu.searcheasy.model.dto.user.UserQueryRequest;
import com.cclu.searcheasy.model.entity.Picture;
import com.cclu.searcheasy.model.vo.PostVO;
import com.cclu.searcheasy.model.vo.SearchVO;
import com.cclu.searcheasy.model.vo.UserVO;
import com.cclu.searcheasy.service.PictureService;
import com.cclu.searcheasy.service.PostService;
import com.cclu.searcheasy.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author ChangCheng Lu
 * @date 2023/7/17 8:56
 */
@RestController
@RequestMapping("/search")
@Slf4j
public class SearchController {

    @Resource
    private PostService postService;

    @Resource
    private PictureService pictureService;

    @Resource
    private UserService userService;

    @Resource
    private SearchFacade searchFacade;

    @PostMapping("/all")
    public BaseResponse<SearchVO> doSearchAll(@RequestBody SearchRequest searchRequest, HttpServletRequest request) {
        String searchText = searchRequest.getSearchText();

        Page<Picture> picturePage = pictureService.searchPicture(searchText, 1, 10);

        PostQueryRequest postQueryRequest = new PostQueryRequest();
        postQueryRequest.setSearchText(searchText);
        Page<PostVO> postVOPage = postService.listPostVOPage(postQueryRequest, request);

        UserQueryRequest userQueryRequest = new UserQueryRequest();
        userQueryRequest.setUserName(searchText);
        Page<UserVO> userVOPage = userService.listUserVOByPage(userQueryRequest, request);

        SearchVO searchVO = new SearchVO();
        searchVO.setPostList(postVOPage.getRecords());
        searchVO.setUserList(userVOPage.getRecords());
        searchVO.setPictureList(picturePage.getRecords());

        return ResultUtils.success(searchVO);
    }

    @PostMapping("/type")
    public BaseResponse<SearchVO> doSearchByType(@RequestBody SearchRequest searchRequest) {
        return ResultUtils.success(searchFacade.doSearchByType(searchRequest));
    }

}
