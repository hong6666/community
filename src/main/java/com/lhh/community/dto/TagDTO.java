package com.lhh.community.dto;

import lombok.Data;

import java.util.List;

/**
 * TagDTO
 *
 * @author 李弘昊
 * @since 2020/6/4
 */
@Data
public class TagDTO {

    /**
     * 标签分类
     */
    private String categoryName;

    /**
     * 标签名
     */
    private List<String> tags;
}
