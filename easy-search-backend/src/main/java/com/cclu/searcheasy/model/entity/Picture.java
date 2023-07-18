package com.cclu.searcheasy.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ChangCheng Lu
 * @date 2023/7/15 23:03
 */
@Data
public class Picture implements Serializable {

    private String title;

    private String url;

}
