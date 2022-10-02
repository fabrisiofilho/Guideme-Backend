package br.com.fabrisio.guideme.service.impl;

import br.com.fabrisio.guideme.entity.notification.NotificationEntity;
import br.com.fabrisio.guideme.repository.NotificationRepository;
import br.com.fabrisio.guideme.repository.UserRepository;
import br.com.fabrisio.guideme.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void create(String text) {
        var users =userRepository.findAllUsersAluno();
        users.forEach(it -> {
            notificationRepository.save(NotificationEntity.builder()
                    .content(text)
                    .user(it)
                    .isRead(false)
                    .build());
        });
    }

}
