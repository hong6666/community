package com.lhh.community.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Comment implements Serializable {
    private Integer id;

    private Integer parentId;

    private Integer type;

    private Integer commentator;

    private Long gmtCreate;

    private Long gmtModified;

    private Integer likeCount;

    private String content;

    private static final long serialVersionUID = 1L;


}