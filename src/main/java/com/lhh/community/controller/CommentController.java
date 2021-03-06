package com.lhh.community.controller;

import com.lhh.community.dto.CommentCreateDTO;
import com.lhh.community.dto.CommentDTO;
import com.lhh.community.dto.ResultDTO;
import com.lhh.community.entity.Comment;
import com.lhh.community.entity.User;
import com.lhh.community.enums.CommentTypeEnum;
import com.lhh.community.exception.CustomizeErrorCode;
import com.lhh.community.services.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @program: community
 * @Date: 2020/1/14 10:06
 * @Author: lhh
 * @Description:
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 新增评论
     */
    @PostMapping("/comment")
    @ResponseBody
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                       HttpServletRequest request)
    {
        //session中获取用户信息
        User user = (User)request.getSession().getAttribute("user");
        //若用户信息为空，则返回错误页面
        if (user == null) {return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);}
        if (commentCreateDTO == null || StringUtils.isBlank(commentCreateDTO.getContent())){
            return ResultDTO.errorOf(CustomizeErrorCode.CONTENT_IS_EMPTY);
        }
        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);
        commentService.insert(comment,user);
        return ResultDTO.okOf();
    }

    /**
     * 通过评论id得到评论下面的二级评论
     */
    @ResponseBody
    @GetMapping("/comment/{id}")
    public ResultDTO<List<CommentDTO>> comments(@PathVariable("id")Long id){
        List<CommentDTO> commentDTOS = commentService.selectByTargetId(id, CommentTypeEnum.COMMENT.getType());
        return ResultDTO.okOf(commentDTOS);
    }
}
