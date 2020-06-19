package com.lhh.community.enums;

/**
 * @program: community
 * @Date: 2020/1/14 14:45
 * @Author: lhh
 * @Description:
 */
public enum CommentTypeEnum {
    /**
     * 问题
     */
    QUESTION(1),
    /**
     * 评论
     */
    COMMENT(2);
    /**
     * 类型
     */
    private Integer type;

    CommentTypeEnum(Integer type)
    {
        this.type = type;
    }

    public Integer getType()
    {
        return type;
    }

    public static boolean isExist(Integer type)
    {
        for(CommentTypeEnum commentTypeEnum:CommentTypeEnum.values())
        {
            if (commentTypeEnum.getType().equals(type))
            {
                return true;
            }
        }
        return false;
    }

}
