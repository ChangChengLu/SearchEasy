package com.cclu.searcheasy.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cclu.searcheasy.model.dto.post.PostQueryRequest;
import com.cclu.searcheasy.model.entity.Post;
import com.cclu.searcheasy.model.vo.PostVO;
import javafx.geometry.Pos;

import javax.servlet.http.HttpServletRequest;

/**
 * 帖子服务
 *
 * ChangCheng Lu
 *  
 */
public interface PostService extends IService<Post> {

    /**
     * 校验
     *
     * @param post
     * @param add
     */
    void validPost(Post post, boolean add);

    /**
     * 获取查询条件
     *
     * @param postQueryRequest
     * @return
     */
    QueryWrapper<Post> getQueryWrapper(PostQueryRequest postQueryRequest);


    /**
     * 获取帖子封装
     *
     * @param post
     * @param request
     * @return
     */
    PostVO getPostVO(Post post, HttpServletRequest request);

    /**
     * 分页获取帖子封装
     *
     * @param postPage
     * @param request
     * @return
     */
    Page<PostVO> getPostVOPage(Page<Post> postPage, HttpServletRequest request);

    /**
     * 获取帖子视图
     * @param postQueryRequest
     * @param request
     * @return
     */
    Page<PostVO> listPostVOPage(PostQueryRequest postQueryRequest, HttpServletRequest request);
}
