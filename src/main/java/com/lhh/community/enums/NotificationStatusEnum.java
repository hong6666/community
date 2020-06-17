package com.lhh.community.enums;

/**
 * NotificationStatusEnum
 * 通知状态枚举类 0为未读，1为已读
 * @author 李弘昊
 * @since 2020/6/16
 */
public enum  NotificationStatusEnum {

    UNREAD(0),READ(1);

    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
