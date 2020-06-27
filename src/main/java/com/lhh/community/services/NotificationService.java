package com.lhh.community.services;

import com.lhh.community.dto.NotificationDTO;
import com.lhh.community.dto.PaginationDTO;
import com.lhh.community.entity.User;

/**
 * NotificationService
 *
 * @author 李弘昊
 * @since 2020/6/17
 */
public interface NotificationService {

    /**
     * 通知未读的数量
     * @param userId userId
     * @return
     */
    Long unreadCount(Long userId);

    /**
     * 阅读通知后更新通知状态为已读
     * @param id id
     * @param user user
     * @return
     */
    NotificationDTO read(Long id, User user);

    /**
     * 分页
     * @param userId userId用户id
     * @param page page多少页
     * @param size size一页多少条数据
     * @return
     */
    PaginationDTO list(Long userId, Integer page, Integer size);
}
