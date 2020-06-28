package com.lhh.community.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 账户id
     */
    private String accountid;

    /**
     * token
     */
    private String token;

    /**
     * 创建时间
     */
    private Long gmtcreate;

    /**
     * 修改时间
     */
    private Long gmtmodified;

    /**
     * 头像地址
     */
    private String avatarUrl;

    private static final long serialVersionUID = 1L;

}