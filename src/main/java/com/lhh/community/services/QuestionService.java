package com.lhh.community.services;

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

    public List<QuestionDTO> questionDTOList();

    /*Question selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKey(Question record);*/

}
