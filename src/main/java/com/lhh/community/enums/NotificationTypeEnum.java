package com.lhh.community.enums;

/**
 * NotificationTypeEnum
 * 通知类型枚举类，1为回复了问题，2为回复了评论
 * @author 李弘昊
 * @since 2020/6/16
 */
public enum  NotificationTypeEnum {

    /**
     * 1,"回复了问题"
     */
    REPLY_QUESTION(1,"回复了问题"),
    /**
     * 2,"回复了评论"
     */
    REPLY_COMMENT(2,"回复了评论");

    private int type;

    private String name;

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    NotificationTypeEnum(int status ,String name) {
        this.type = status;
        this.name = name;
    }

    /**
     * 通过类型得到名称
     * @param type
     * @return
     */
    public static  String nameOfType(int type) {
        for (NotificationTypeEnum notificationTypeEnum : NotificationTypeEnum.values()){
            if (notificationTypeEnum.getType() == type) {
                return notificationTypeEnum.getName();
            }
        }
        return "";
    }

}
