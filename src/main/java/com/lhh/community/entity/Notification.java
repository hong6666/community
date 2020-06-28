package com.lhh.community.entity;

import lombok.Data;

/**
 * Notification
 *
 * @author 李弘昊
 * @since 2020/6/16
 */
@Data
public class Notification {
    /**
     * 通知id
     */
    private Long id;

    /**
     * 通知人
     */
    private Long notifier;

    /**
     * 接收人
     */
    private Long receiver;

    /**
     * 外部问题id
     */
    private Long outerid;

    /**
     * 类型,1,回复了问题;2,回复了评论
     */
    private Integer type;

    /**
     * 创建时间
     */
    private Long gmtCreate;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 通知人姓名
     */
    private String notifierName;

    /**
     * 外部标题
     */
    private String outerTitle;

}

