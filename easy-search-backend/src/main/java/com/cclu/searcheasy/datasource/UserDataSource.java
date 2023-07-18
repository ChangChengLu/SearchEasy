package com.cclu.searcheasy.datasource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cclu.searcheasy.model.dto.user.UserQueryRequest;
import com.cclu.searcheasy.model.vo.UserVO;
import com.cclu.searcheasy.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author ChangCheng Lu
 * @date 2023/7/18 9:12
 */
@Service
public class UserDataSource implements DataSource<UserVO> {

    @Resource
    private UserService userService;

    @Override
    public Page<UserVO> doSearch(String searchText, long pageNum, long pageSize) {
        UserQueryRequest userQueryRequest = new UserQueryRequest();
        userQueryRequest.setUserName(searchText);
        userQueryRequest.setCurrent(pageNum);
        userQueryRequest.setPageSize(pageSize);

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(servletRequestAttributes).getRequest();

        return userService.listUserVOByPage(userQueryRequest, request);
    }
}
