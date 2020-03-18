package com.lhh.community.dto;

import lombok.Data;

/**
 * @program: community
 * @Date: 2020/3/17 15:53
 * @Author: lhh
 * @Description:
 */
@Data
public class CommentCreateDTO {
    private Integer parentId;
    private String content;
    private Integer type;
}
