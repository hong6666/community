package com.lhh.community.services;

import com.lhh.community.dto.PaginationDTO;
import com.lhh.community.dto.QuestionDTO;
import com.lhh.community.entity.Question;

import java.util.List;

/**
 * @program: community
 * @Date: 2020/1/3 9:54
 * @Author: lhh
 * @Description:
 */
public interface QuestionService {

    int insert(Question record);

    List<Question> list();

    List<QuestionDTO> questionDTOList();

    PaginationDTO questionPage(Integer page, Integer size);

    PaginationDTO questionPage(Long userId,Integer page,Integer size);

    int count();

    int countByUserId(Long userId);

    /**
     * 通过问题id得到问题的传输实体类
     * @param id 问题id
     * @return QuestionDTO
     */
    QuestionDTO selectByPrimaryKey(Long id);

    /**
     * 创建或更新问题
     * @param question
     */
    void createOrUpdate(Question question);

    /**
     * 阅读数加
     * @param id 问题id
     */
    void incView(Long id);

    void incCommentCount(Question question);

    List<QuestionDTO> selectRelated(QuestionDTO question);

    /*int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKey(Question record);*/

}
