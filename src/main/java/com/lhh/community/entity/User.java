package com.lhh.community.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private Long id;

    private String name;

    private String accountid;

    private String token;

    private Long gmtcreate;

    private Long gmtmodified;

    private String avatarUrl;

    private static final long serialVersionUID = 1L;

}