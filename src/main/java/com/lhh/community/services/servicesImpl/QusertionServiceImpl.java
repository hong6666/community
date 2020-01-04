package com.lhh.community.services.servicesImpl;

import com.lhh.community.dao.QuestionMapper;
import com.lhh.community.dao.UserMapper;
import com.lhh.community.dto.QuestionDTO;
import com.lhh.community.entity.Question;
import com.lhh.community.entity.User;
import com.lhh.community.services.QuestionService;
import com.lhh.community.utils.LogUtil;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: community
 * @Date: 2020/1/3 10:00
 * @Author: lhh
 * @Description:
 */
@Service
public class QusertionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    private Logger logger = LogUtil.logger(this.getClass());

    @Override
    public int insert(Question record) {
        try {
            if(record != null)
            {
                int result = questionMapper.insert(record);
                logger.info("问题插入成功");
                return result;
            }else
            {
                logger.info("问题为空");
                return -1;
            }
        } catch (Exception e) {
            logger.info("异常");
            return -1;
        }

    }

    @Override
    public List<Question> list() {
        return questionMapper.selectAll();
    }

    @Override
    public List<QuestionDTO> questionDTOList()
    {
        List<Question> questions = questionMapper.selectAll();
        List<QuestionDTO> questionDTOs = new ArrayList<>();
        for(Question question : questions)
        {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOs.add(questionDTO);
        }
        return questionDTOs;
    }


}
