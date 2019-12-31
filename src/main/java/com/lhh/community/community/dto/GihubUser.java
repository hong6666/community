package com.lhh.community.community.dto;

import lombok.Data;

/**
 * @program: community
 * @Date: 2019/12/31 9:46
 * @Author: lhh
 * @Description:
 */
@Data
public class GihubUser {
    private String login;
    private Long id;
    private String bio;
}
