package com.lhh.community.dto;

import lombok.Data;

/**
 * @program: community
 * @Date: 2020/3/17 15:53
 * @Author: lhh
 * @Description:
 */
@Data
public class CommentCreateDTO {

    /**
     * 评论的父id
     */
    private Long parentId;

    /**
     * 内容
     */
    private String content;

    /**
     * 类型
     */
    private Integer type;
}
