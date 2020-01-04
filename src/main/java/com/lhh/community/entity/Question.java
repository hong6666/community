package com.lhh.community.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Question implements Serializable {

    private Integer id;

    private String title;

    private Long gmtCreate;

    private Long gmtModified;

    private Integer creator;

    private Integer commentCount;

    private Integer viewCount;

    private Integer likeCount;

    private String tag;

    private String description;

    private static final long serialVersionUID = 1L;

}