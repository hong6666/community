package com.lhh.community.services;

import com.lhh.community.dto.CommentDTO;
import com.lhh.community.entity.Comment;

import java.util.List;

/**
 * @program: community
 * @Date: 2020/1/14 10:26
 * @Author: lhh
 * @Description:
 */
public interface CommentService {

    void insert(Comment record);

    List<CommentDTO> selectByTargetId(Integer id,Integer type);
}
