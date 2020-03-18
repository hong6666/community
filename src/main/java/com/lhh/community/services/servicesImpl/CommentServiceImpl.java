package com.lhh.community.services.servicesImpl;

import com.lhh.community.dao.CommentMapper;
import com.lhh.community.dao.QuestionMapper;
import com.lhh.community.dao.UserMapper;
import com.lhh.community.dto.CommentDTO;
import com.lhh.community.entity.Comment;
import com.lhh.community.entity.Question;
import com.lhh.community.entity.User;
import com.lhh.community.enums.CommentTypeEnum;
import com.lhh.community.exception.CustomizeErrorCode;
import com.lhh.community.exception.CustomizeException;
import com.lhh.community.services.CommentService;
import com.lhh.community.utils.LogUtil;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @program: community
 * @Date: 2020/1/14 10:29
 * @Author: lhh
 * @Description:
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    private Logger logger = LogUtil.logger(this.getClass());

    @Override
    @Transactional
    public void insert(Comment comment) {
        if(comment.getParentId() == null || comment.getParentId() == 0)
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        if(comment.getType() == null || !CommentTypeEnum.isExist(comment.getType()))
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        if(comment.getType() == CommentTypeEnum.COMMENT.getType())
        {
            //回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbComment == null)
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            commentMapper.insert(comment);
        }else
        {
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question == null)
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionMapper.incCommentCount(question);
        }
    }

    @Override
    public List<CommentDTO> listByQuestionId(Integer id) {
        List<Comment> comments = commentMapper.selectByQuestionId(id);

        if (comments.size() == 0){
            return new ArrayList<>();
        }

        //获取去重的评论人
        Set<Integer> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Integer> userIds = new ArrayList<>();
        userIds.addAll(commentators);

        //获取评论人并转换为Map
        List<User> users = userMapper.selectByIds(userIds);
        Map<Integer,User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(),user -> user));

        //转换comment为commentDTO
        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment,commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());
        return commentDTOS;
    }

}
