package com.electronicsmanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.electronicsmanagement.entity.Stock;
import com.electronicsmanagement.enums.StockStatus;

public interface StockRepository extends JpaRepository<Stock, Long> {
//
//    @Query("""
//       SELECT COUNT(s)
//       FROM Stock s
//       WHERE s.product.id = :productId AND s.status = 'AVAILABLE'
//    """)
//    int countAvailableStock(Long productId);

	@Query("""
			   SELECT COUNT(s)
			   FROM Stock s
			   WHERE s.product.id = :productId
			   AND s.status = com.electronicsmanagement.enums.StockStatus.AVAILABLE
			""")
			int countAvailableStock(@Param("productId") Long productId);

	
	
    Optional<Stock> findFirstByProduct_IdAndStatus(Long productId, StockStatus status);

	//List<Stock> findAvailableStockByProduct(Long id);

    @Query("""
            SELECT s
            FROM Stock s
            WHERE s.product.id = :productId
            AND s.status = com.electronicsmanagement.enums.StockStatus.AVAILABLE
            ORDER BY s.id
            """)
     List<Stock> findAvailableStockByProduct(@Param("productId") Long productId);


}
