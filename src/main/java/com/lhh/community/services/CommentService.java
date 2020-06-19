package com.lhh.community.services;

import com.lhh.community.dto.CommentDTO;
import com.lhh.community.entity.Comment;
import com.lhh.community.entity.User;

import java.util.List;

/**
 * @program: community
 * @Date: 2020/1/14 10:26
 * @Author: lhh
 * @Description:
 */
public interface CommentService {

    /**
     * 插入评论
     * @param record 评论记录
     * @param commentator 评论人
     */
    void insert(Comment record, User commentator);

    /**
     * 通过问题或者评论的id和类型，获取评论或者二级评论
     * @param id 问题或者评论的id
     * @param type 类型是
     * @return
     */
    List<CommentDTO> selectByTargetId(Integer id,Integer type);
}
