package com.cclu.searcheasy.model.dto.search;

import com.cclu.searcheasy.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author ChangCheng Lu
 * @date 2023/7/17 8:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SearchRequest extends PageRequest implements Serializable {

    private String searchText;

    private String type;

}
