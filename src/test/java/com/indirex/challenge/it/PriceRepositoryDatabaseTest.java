package com.indirex.challenge.it;

import com.inditex.challenge.Application;
import com.inditex.challenge.infrastructure.entity.PricePersistence;
import com.inditex.challenge.infrastructure.persistence.IPriceJpaPersistenceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PriceRepositoryDatabaseTest {

    @Autowired
    IPriceJpaPersistenceRepository priceJpaPersistenceRepository;

    @Test
    void searchPricesOk_IT() {
        Long productId = 35455L;
        Long brandId = 1L;
        List<PricePersistence> pricePersistenceAllList = priceJpaPersistenceRepository.findAll();
        List<PricePersistence> pricePersistenceFilteredList = priceJpaPersistenceRepository.findByProductIdAndBrandId(productId, brandId);

        assertTrue(pricePersistenceAllList.size() >= pricePersistenceFilteredList.size());
        assertFalse(pricePersistenceFilteredList.isEmpty());
        assertEquals(productId, pricePersistenceFilteredList.get(0).getProductId());
        assertEquals(brandId, pricePersistenceFilteredList.get(0).getBrandId());
    }
}
