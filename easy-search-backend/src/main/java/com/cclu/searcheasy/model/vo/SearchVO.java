package com.cclu.searcheasy.model.vo;

import com.cclu.searcheasy.model.entity.Picture;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author ChangCheng Lu
 * @date 2023/7/17 8:56
 */
@Data
public class SearchVO implements Serializable {

    List<PostVO> postList;

    List<Picture> pictureList;

    List<UserVO> userList;

    List<?> dataList;

}
