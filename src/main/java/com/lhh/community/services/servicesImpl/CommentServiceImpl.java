package com.lhh.community.services.servicesImpl;

import com.lhh.community.dao.CommentMapper;
import com.lhh.community.dao.NotificationMapper;
import com.lhh.community.dao.QuestionMapper;
import com.lhh.community.dao.UserMapper;
import com.lhh.community.dto.CommentDTO;
import com.lhh.community.entity.Comment;
import com.lhh.community.entity.Notification;
import com.lhh.community.entity.Question;
import com.lhh.community.entity.User;
import com.lhh.community.enums.CommentTypeEnum;
import com.lhh.community.enums.NotificationStatusEnum;
import com.lhh.community.enums.NotificationTypeEnum;
import com.lhh.community.exception.CustomizeErrorCode;
import com.lhh.community.exception.CustomizeException;
import com.lhh.community.services.CommentService;
import com.lhh.community.utils.LogUtil;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private CommentMapper commentMapper;

    private QuestionMapper questionMapper;

    private UserMapper userMapper;

    private NotificationMapper notificationMapper;

    private Logger logger = LogUtil.logger(this.getClass());

    @Override
    @Transactional(rollbackFor = {Throwable.class,Exception.class})
    public void insert(Comment comment,User commentator) {
        if(comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if(comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if(comment.getType().equals(CommentTypeEnum.COMMENT.getType()))
        {
            //回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbComment == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }

            //回复问题
            Question question = questionMapper.selectByPrimaryKey(dbComment.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }

            commentMapper.insert(comment);

            //增加评论的评论数
            Comment parentComment = new Comment();
            parentComment.setId(comment.getParentId());
            parentComment.setCommentCount(1L);
            commentMapper.incCommentCount(parentComment);

            //创建通知
            createNotify(comment,question.getCreator(),commentator.getName(),
                    question.getTitle(),NotificationTypeEnum.REPLY_COMMENT,question.getId());
        }else
        {
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            //增加问题的评论数
            question.setCommentCount(1L);
            questionMapper.incCommentCount(question);
        }
    }

    /**
     * 创建通知
     * @param comment 评论
     * @param receiver 接收人
     * @param notifierName 通知人名字
     * @param outerTitle 外部标题
     * @param notificationType 通知类型
     * @param outerId 外部标题id
     */
    private void createNotify(Comment comment, Long receiver, String notifierName, String outerTitle, NotificationTypeEnum notificationType,Long outerId) {
        Notification notification = new Notification();
        //创建时间
        notification.setGmtCreate(System.currentTimeMillis());
        //通知类型
        notification.setType(notificationType.getType());
        notification.setOuterid(outerId);
        notification.setNotifier(comment.getCommentator());
        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
        notification.setReceiver(receiver);
        notification.setNotifierName(notifierName);
        notification.setOuterTitle(outerTitle);
        notificationMapper.insert(notification);
    }

    @Override
    public List<CommentDTO> selectByTargetId(Long id,Integer type) {
        List<Comment> comments = commentMapper.selectByTargetId(id,type);

        if (comments.size() == 0){
            return new ArrayList<>();
        }

        //获取去重的评论人
        Set<Long> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Long> userIds = new ArrayList<>();
        userIds.addAll(commentators);

        //获取评论人并转换为Map
        List<User> users = userMapper.selectByIds(userIds);
        Map<Long,User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(),user -> user));

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
