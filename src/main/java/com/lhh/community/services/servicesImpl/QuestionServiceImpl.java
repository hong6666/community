package com.lhh.community.services.servicesImpl;

import com.lhh.community.dao.QuestionMapper;
import com.lhh.community.dao.UserMapper;
import com.lhh.community.dto.PaginationDTO;
import com.lhh.community.dto.QuestionDTO;
import com.lhh.community.entity.Question;
import com.lhh.community.entity.User;
import com.lhh.community.exception.CustomizeErrorCode;
import com.lhh.community.exception.CustomizeException;
import com.lhh.community.services.QuestionService;
import com.lhh.community.utils.LogUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: community
 * @Date: 2020/1/3 10:00
 * @Author: lhh
 * @Description:
 */
@Service
public class QuestionServiceImpl implements QuestionService {

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
        /*for(Question question : questions)
        {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOs.add(questionDTO);
        }*/
        return questionDTOs;
    }

    @Override
    public PaginationDTO questionPage(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        Integer totalCount = questionMapper.count();

        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        if(page < 1){ page = 1;}
        if(page > totalPage) {page = totalPage;}

        paginationDTO.setPagination(totalPage,page);

        Integer offset = size * (page -1);

        List<Question> questions = questionMapper.selectPage(offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for(Question question : questions)
        {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setData(questionDTOList);
        return paginationDTO;
    }

    @Override
    public PaginationDTO questionPage(Long userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        Integer totalCount = questionMapper.countByUserId(userId);
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if(page < 1) {page = 1;}
        if(page > totalPage) {page = totalPage;}

        paginationDTO.setPagination(totalPage,page);
        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.selectPageByUserId(userId,offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for(Question question : questions)
        {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setData(questionDTOList);
        return paginationDTO;
    }

    @Override
    public int count() {
        return questionMapper.count();
    }

    @Override
    public int countByUserId(Long userId) {
        return questionMapper.countByUserId(userId);
    }

    @Override
    public QuestionDTO selectByPrimaryKey(Long id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if(question == null){throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);}
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    @Override
    public void createOrUpdate(Question question) {
        if (question.getId() == null)
        {
            //创建插入
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setViewCount(0L);
            question.setLikeCount(0L);
            question.setCommentCount(0L);
            questionMapper.insert(question);
            logger.info("问题插入成功");
        }else
        {
            //更新
            question.setGmtModified(System.currentTimeMillis());
            int updateResult = questionMapper.updateByPrimaryKey(question);
            if(updateResult != 1) {throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);}
            logger.info("问题id="+question.getId()+"更新了");
        }
    }

    @Override
    public void incView(Long id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1L);
        int result = questionMapper.incView(question);
        if (result != 1) {logger.info("阅读数加一失败");}
    }

    @Override
    public void incCommentCount(Question question) {
        questionMapper.incCommentCount(question);
        Question test = questionMapper.selectByPrimaryKey(question.getId());
        logger.info("评论加一，id="+question.getId()+"count="+test.getCommentCount());
    }

    @Override
    public List<QuestionDTO> selectRelated(QuestionDTO question) {
        if (StringUtils.isBlank(question.getTag()))
        {
            return new ArrayList<>();
        }
        String[] tags = StringUtils.split(question.getTag(),",");
        String regexpTag = Arrays.stream(tags).collect(Collectors.joining("|"));
        Question qt = new Question();
        qt.setId(question.getId());
        qt.setTag(regexpTag);
        List<Question> questions = questionMapper.selectRelated(qt);
        List<QuestionDTO> questionDTOS = questions.stream().map(q -> {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(q,questionDTO);
            return questionDTO;
        }).collect(Collectors.toList());
        return questionDTOS;
    }

}
