package com.lhh.community.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Question implements Serializable {

    private Long id;

    private String title;

    private Long gmtCreate;

    private Long gmtModified;

    private Long creator;

    private Long commentCount;

    private Long viewCount;

    private Long likeCount;

    private String tag;

    private String description;

    private static final long serialVersionUID = 1L;

}