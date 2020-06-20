package com.lhh.community.dto;

import lombok.Data;

/**
 * @program: community
 * @Date: 2019/12/30 22:40
 * @Author: lhh
 * @Description:
 */
@Data
public class AccessTokenDTO {

    /**
     *
     */
    private String client_id;

    /**
     *
     */
    private String client_secret;

    /**
     *
     */
    private String code;

    /**
     *
     */
    private String redirect_uri;

    /**
     *
     */
    private String state;

}
