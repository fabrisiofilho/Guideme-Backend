package br.com.fabrisio.guideme.repository;

import br.com.fabrisio.guideme.entity.store.ItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    @Query("SELECT p FROM ItemEntity p WHERE UPPER(p.title) LIKE CONCAT('%',UPPER(:title) ,'%')")
    Page<ItemEntity> search(String title, Pageable pageable);

}
