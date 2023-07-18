package com.cclu.searcheasy.datasource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cclu.searcheasy.common.ErrorCode;
import com.cclu.searcheasy.exception.ThrowUtils;
import com.cclu.searcheasy.model.dto.post.PostQueryRequest;
import com.cclu.searcheasy.model.entity.Post;
import com.cclu.searcheasy.model.vo.PostVO;
import com.cclu.searcheasy.service.PostService;
import javafx.geometry.Pos;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author ChangCheng Lu
 * @date 2023/7/18 9:06
 */
@Service
public class PostDataSource implements DataSource<PostVO> {

    @Resource
    private PostService postService;

    @Override
    public Page<PostVO> doSearch(String searchText, long pageNum, long pageSize) {
        PostQueryRequest postQueryRequest = new PostQueryRequest();
        postQueryRequest.setSearchText(searchText);
        postQueryRequest.setCurrent(pageNum);
        postQueryRequest.setPageSize(pageSize);

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(servletRequestAttributes).getRequest();

        return postService.listPostVOPage(postQueryRequest, request);
    }
}
