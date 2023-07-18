package com.cclu.searcheasy.model.dto.picture;

import com.cclu.searcheasy.common.PageRequest;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ChangCheng Lu
 * @date 2023/7/16 9:20
 */
@Data
public class PictureQueryRequest extends PageRequest implements Serializable {

    private String searchText;

}
