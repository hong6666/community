package com.lhh.community.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Question implements Serializable {

    /**
     * 问题id
     */
    private Long id;

    /**
     * 问题标题
     */
    private String title;

    /**
     * 创建时间
     */
    private Long gmtCreate;

    /**
     * 修改时间
     */
    private Long gmtModified;

    /**
     * 创建人
     */
    private Long creator;

    /**
     * 评论数
     */
    private Long commentCount;

    /**
     * 查看数
     */
    private Long viewCount;

    /**
     * 收藏数
     */
    private Long likeCount;

    /**
     * 标签
     */
    private String tag;

    /**
     * 描述
     */
    private String description;

    private static final long serialVersionUID = 1L;

}