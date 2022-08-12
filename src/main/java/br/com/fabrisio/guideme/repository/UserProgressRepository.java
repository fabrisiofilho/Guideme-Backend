package br.com.fabrisio.guideme.repository;

import br.com.fabrisio.guideme.entity.user.UserProgressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProgressRepository extends JpaRepository<UserProgressEntity, Long> {
}
