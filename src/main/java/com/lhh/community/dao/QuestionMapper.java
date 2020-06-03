package com.lhh.community.dao;

import com.lhh.community.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface QuestionMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Question record);

    int insertSelective(Question record);

    Question selectByPrimaryKey(Integer id);


    List<Question> selectAll();

    List<Question> selectPage(@Param("offset")Integer offset,@Param("size")Integer size);

    List<Question> selectPageByUserId(@Param("userId")Integer userId,@Param("offset")Integer offset,@Param("size")Integer size);

    int count();

    int countByUserId(@Param("userId")Integer userId);

    int updateByPrimaryKeySelective(Question record);


    int updateByPrimaryKeyWithBLOBs(Question record);


    int updateByPrimaryKey(Question record);

    int incView(Question question);

    int incCommentCount(Question question);

    /**
     * 查询相关问题列表
     * @param question 问题对象
     * @return 相关问题列表
     */
    List<Question> selectRelated(Question question);
}