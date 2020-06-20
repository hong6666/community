package com.lhh.community.dto;

import lombok.Data;

/**
 * NotificationDTO
 *
 * @author 李弘昊
 * @since 2020/6/16
 */
@Data
public class NotificationDTO {

    /**
     * id
     */
    private Long id;

    /**
     * 创建时间
     */
    private Long gmtCreate;

    /**
     * 状态
     */
    private Integer status;

    /**
     *
     */
    private Long notifier;

    /**
     *
     */
    private String notifierName;

    /**
     *
     */
    private String outerTitle;

    /**
     *
     */
    private Long outerid;

    /**
     *
     */
    private String typeName;

    /**
     *
     */
    private Integer type;


}
