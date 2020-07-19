package com.lhh.community.dao;

import com.lhh.community.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface QuestionMapper {

    int deleteByPrimaryKey(Long id);

    /**
     * 插入问题
     * @param record record
     * @return int
     */
    int insert(Question record);


    Question selectByPrimaryKey(Long id);


    List<Question> selectAll();

    List<Question> selectPage(@Param("offset")Integer offset,@Param("size")Integer size);

    List<Question> selectPageByUserId(@Param("userId")Long userId,@Param("offset")Integer offset,@Param("size")Integer size);

    int count();

    int countByUserId(@Param("userId")Long userId);

//    int updateByPrimaryKeySelective(Question record);


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