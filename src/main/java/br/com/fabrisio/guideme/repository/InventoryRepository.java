package br.com.fabrisio.guideme.repository;

import br.com.fabrisio.guideme.entity.user.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {
}
