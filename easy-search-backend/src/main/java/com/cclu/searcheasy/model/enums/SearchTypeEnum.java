package com.cclu.searcheasy.model.enums;

import com.cclu.searcheasy.common.ErrorCode;
import com.cclu.searcheasy.exception.BusinessException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ChangCheng Lu
 * @date 2023/7/18 8:43
 */
public enum SearchTypeEnum {
    /**
     * type
     */
    POST("帖⼦","post"),
    USER("⽤户","user"),
    PICTURE("图⽚","picture"),
    VIDEO("视频","video");


    private String text;

    private String value;

    SearchTypeEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public static List<String> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    public static SearchTypeEnum getSearchTypeByValue(String value) {
        if (value == null || "".equals(value)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        SearchTypeEnum[] searchTypeEnums = values();
        for (SearchTypeEnum searchTypeEnum : searchTypeEnums) {
            if (searchTypeEnum.value.equals(value)) {
                return searchTypeEnum;
            }
        }
        return null;
    }

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }
}
