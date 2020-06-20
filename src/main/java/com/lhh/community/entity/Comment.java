package com.lhh.community.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Comment implements Serializable {
    /**
     * 主键
     */
    private Long id;
    /**
     * 上级id
     */
    private Long parentId;
    /**
     * 类型，QUESTION(1),COMMENT(2);
     */
    private Integer type;
    /**
     * 评论创建者
     */
    private Long commentator;
    /**
     * 评论创建时间
     */
    private Long gmtCreate;
    /**
     * 评论修改时间
     */
    private Long gmtModified;
    /**
     * 收藏数
     */
    private Long likeCount;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 下级评论数量
     */
    private Long commentCount;


    private static final long serialVersionUID = 1L;


}