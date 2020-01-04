package com.lhh.community.dto;

import com.lhh.community.entity.User;
import lombok.Data;

/**
 * @program: community
 * @Date: 2020/1/4 9:55
 * @Author: lhh
 * @Description:
 */
@Data
public class QuestionDTO {

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

    private User user;

}
