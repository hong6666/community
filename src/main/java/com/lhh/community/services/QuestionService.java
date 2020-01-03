package com.lhh.community.services;

import com.lhh.community.dto.Question;

/**
 * @program: community
 * @Date: 2020/1/3 9:54
 * @Author: lhh
 * @Description:
 */
public interface QuestionService {

    int insert(Question record);

    /*Question selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKey(Question record);*/

}
