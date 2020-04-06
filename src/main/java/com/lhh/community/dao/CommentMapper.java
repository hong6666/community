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

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    List<Comment> selectByTargetId(@Param("id") Integer id, @Param("type") Integer type);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
}