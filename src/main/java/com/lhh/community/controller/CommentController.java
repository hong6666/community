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

    @PostMapping("/comment")
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                       HttpServletRequest request)
    {
        User user = (User)request.getSession().getAttribute("user");
        if (user == null) return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
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
        comment.setLikeCount(0);
        commentService.insert(comment);
        return ResultDTO.okOf();
    }

    @ResponseBody
    @GetMapping("/comment/{id}")
    public ResultDTO<List<CommentDTO>> comments(@PathVariable("id")Integer id){
        List<CommentDTO> commentDTOS = commentService.selectByTargetId(id, CommentTypeEnum.COMMENT.getType());
        return ResultDTO.okOf(commentDTOS);
    }
}
