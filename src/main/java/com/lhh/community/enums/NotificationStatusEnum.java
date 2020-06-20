package com.lhh.community.enums;

/**
 * NotificationStatusEnum
 * 通知状态枚举类 0为未读，1为已读
 * @author 李弘昊
 * @since 2020/6/16
 */
public enum  NotificationStatusEnum {

    /**
     * 未读为0
     */
    UNREAD(0),

    /**
     * 已读为1
     */
    READ(1);

    /**
     * 状态
     */
    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
