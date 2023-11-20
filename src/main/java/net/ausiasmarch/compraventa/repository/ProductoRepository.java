package net.ausiasmarch.compraventa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.ausiasmarch.compraventa.entity.ProductoEntity;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {

    @Query(value = "SELECT p.*,count(c.id) FROM producto p, compra c WHERE p.id = c.id_producto GROUP BY p.id ORDER BY COUNT(p.id) desc", nativeQuery = true)
    Page<ProductoEntity> findProductosConMasCompras(Pageable pageable);

    @Modifying
    @Query(value = "ALTER TABLE producto AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();

    
} 