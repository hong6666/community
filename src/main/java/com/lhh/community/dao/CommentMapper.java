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
    /**
     * 物理删除
     * @param id 评论id
     * @return 删除条数
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入评论
     * @param record 评论记录
     * @return 插入成功数量
     */
    int insert(Comment record);

//    int insertSelective(Comment record);

    /**
     * 通过评论id得到评论详情
     * @param id 评论id
     * @return 评论信息
     */
    Comment selectByPrimaryKey(Long id);

    /**
     * 通过id和类型得到问题的评论或者评论的二级评论
     * @param id id
     * @param type type
     * @return List<Comment>
     */
    List<Comment> selectByTargetId(@Param("id") Long id, @Param("type") Integer type);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    /**
     * 增加评论的评论数
     * @param parentComment 父级评论
     */
    void incCommentCount(@Param("parentComment") Comment parentComment);
}