package com.lhh.community.dto;

import lombok.Data;

/**
 * @program: community
 * @Date: 2019/12/31 9:46
 * @Author: lhh
 * @Description:
 */
@Data
public class GithubUser {
    private String login;
    private Long id;
    private String bio;
}
