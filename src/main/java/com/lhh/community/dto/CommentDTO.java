package com.lhh.community.dto;

import com.lhh.community.entity.User;
import lombok.Data;

/**
 * @program: community
 * @Date: 2020/1/14 10:09
 * @Author: lhh
 * @Description:
 */
@Data
public class CommentDTO {

    private Long id;

    private Long parentId;

    private Integer type;

    private Long commentator;

    private Long gmtCreate;

    private Long gmtModified;

    private Integer likeCount;

    private String content;

    private Integer commentCount;

    private User user;
}
