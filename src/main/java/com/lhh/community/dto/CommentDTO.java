package com.lhh.community.dto;

import lombok.Data;

/**
 * @program: community
 * @Date: 2020/1/14 10:09
 * @Author: lhh
 * @Description:
 */
@Data
public class CommentDTO {
    private Integer parentId;
    private String content;
    private Integer type;
}
