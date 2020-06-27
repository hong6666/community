package com.lhh.community.dao;

import com.lhh.community.entity.Comment;
import com.lhh.community.enums.CommentTypeEnum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

//    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long id);

    List<Comment> selectByTargetId(@Param("id") Long id, @Param("type") Integer type);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    /**
     * 增加评论的评论数
     * @param parentComment 父级评论
     */
    void incCommentCount(@Param("parentComment") Comment parentComment);
}