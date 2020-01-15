package com.lhh.community.enums;

/**
 * @program: community
 * @Date: 2020/1/14 14:45
 * @Author: lhh
 * @Description:
 */
public enum CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);
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
            if (commentTypeEnum.getType() == type)
            {
                return true;
            }
        }
        return false;
    }

}
