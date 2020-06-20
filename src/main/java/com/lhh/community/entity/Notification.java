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
     * id
     */
    private Long id;

    /**
     * 要通知的人
     */
    private Long notifier;

    /**
     *
     */
    private Long receiver;

    /**
     *
     */
    private Long outerid;

    /**
     *
     */
    private Integer type;

    /**
     *
     */
    private Long gmtCreate;

    /**
     *
     */
    private Integer status;

    /**
     *
     */
    private String notifierName;

    /**
     *
     */
    private String outerTitle;

}

