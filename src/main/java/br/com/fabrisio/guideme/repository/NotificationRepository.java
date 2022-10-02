package br.com.fabrisio.guideme.repository;

import br.com.fabrisio.guideme.entity.notification.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
}
