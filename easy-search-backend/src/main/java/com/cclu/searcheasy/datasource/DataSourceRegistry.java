package com.cclu.searcheasy.datasource;

import com.cclu.searcheasy.common.ErrorCode;
import com.cclu.searcheasy.exception.BusinessException;
import com.cclu.searcheasy.model.enums.SearchTypeEnum;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ChangCheng Lu
 * @date 2023/7/18 9:21
 */
@Component
public class DataSourceRegistry {

    @Resource
    private PostDataSource postDataSource;

    @Resource
    private UserDataSource userDataSource;

    @Resource
    private PictureDataSource pictureDataSource;

    private Map<String, DataSource<T>> typeDataSourceMap;

    @PostConstruct
    public void doInit() {
        typeDataSourceMap = new HashMap() {{
            put(SearchTypeEnum.POST.getValue(), postDataSource);
            put(SearchTypeEnum.USER.getValue(), userDataSource);
            put(SearchTypeEnum.PICTURE.getValue(), pictureDataSource);
        }};
    }

    public DataSource<T> getDataSourceByType(String type) {
        DataSource<T> tDataSource = typeDataSourceMap.get(type);
        if (tDataSource == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return tDataSource;
    }

}
