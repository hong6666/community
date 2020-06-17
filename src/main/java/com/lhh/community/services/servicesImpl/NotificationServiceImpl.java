package com.lhh.community.services.servicesImpl;

import com.lhh.community.dao.NotificationMapper;
import com.lhh.community.dto.NotificationDTO;
import com.lhh.community.dto.PaginationDTO;
import com.lhh.community.entity.Notification;
import com.lhh.community.entity.User;
import com.lhh.community.enums.NotificationTypeEnum;
import com.lhh.community.services.NotificationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * NotificationServiceImpl
 *
 * @author 李弘昊
 * @since 2020/6/17
 */
@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    @Override
    public Long unreadCount(Long userId) {
        return null;
    }

    @Override
    public NotificationDTO read(Long id, User user) {
        return null;
    }

    @Override
    public PaginationDTO list(Integer userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        Integer totalCount = notificationMapper.countByUserId(userId);
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if(page < 1) page = 1;
        if(page > totalPage) page = totalPage;

        paginationDTO.setPagination(totalPage,page);
        Integer offset = size * (page - 1);
        List<Notification> notifications = notificationMapper.selectPageByUserId(userId,offset,size);

        if (notifications.size() == 0) {
            return paginationDTO;
        }

        List<NotificationDTO> notificationDTOS  = new ArrayList<>();
        for (Notification notification : notifications) {
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification, notificationDTO);
            notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
            notificationDTOS.add(notificationDTO);
        }
        paginationDTO.setData(notificationDTOS);
        return paginationDTO;
    }
}
