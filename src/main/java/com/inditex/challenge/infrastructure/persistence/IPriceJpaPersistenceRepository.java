package com.inditex.challenge.infrastructure.persistence;

import com.inditex.challenge.infrastructure.entity.PricePersistence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPriceJpaPersistenceRepository extends JpaRepository<PricePersistence, Long> {
    List<PricePersistence> findByProductIdAndBrandId(Long productId, Long brandId);
}
