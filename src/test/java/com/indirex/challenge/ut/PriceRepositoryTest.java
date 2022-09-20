package com.indirex.challenge.ut;

import com.inditex.challenge.business.domain.entity.Price;
import com.inditex.challenge.business.domain.vo.Currency;
import com.inditex.challenge.business.domain.vo.DateRange;
import com.inditex.challenge.business.exception.PriceNotFoundException;
import com.inditex.challenge.infrastructure.entity.PricePersistence;
import com.inditex.challenge.infrastructure.repository.PriceRepository;
import com.inditex.challenge.infrastructure.persistence.IPriceJpaPersistenceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceRepositoryTest {

    @InjectMocks
    PriceRepository priceRepository;

    @Mock
    IPriceJpaPersistenceRepository priceJpaPersistenceRepositoryMock;

    @Test
    void retrievePriceOkUTest() {
        LocalDateTime date = LocalDateTime.now();
        DateRange dateRange = new DateRange(date, date.plusDays(1));
        Long productId = 35455L;
        Long brandId = 1L;
        Float finalPrice = 30.5f;
        List<PricePersistence> pricePersistenceList = buildPricePersistenceList(dateRange, finalPrice);

        when(priceJpaPersistenceRepositoryMock.findByProductIdAndBrandId(productId, brandId)).thenReturn(pricePersistenceList);
        Price price = priceRepository.retrievePrice(date, productId, brandId);
        assertEquals(finalPrice, price.price());
    }

    @Test
    void retrievePriceKoNoResultsUTest() {
        LocalDateTime date = LocalDateTime.now();
        Long productId = 35455L;
        Long brandId = 1L;

        when(priceJpaPersistenceRepositoryMock.findByProductIdAndBrandId(productId, brandId)).thenReturn(Collections.emptyList());
        assertThrows(PriceNotFoundException.class, () -> priceRepository.retrievePrice(date, productId, brandId));
    }

    @Test
    void retrievePriceKoNoMatchingDateUTest() {
        LocalDateTime date = LocalDateTime.now();
        DateRange dateRange = new DateRange(date.plusSeconds(1), date.plusDays(1));
        Long productId = 35455L;
        Long brandId = 1L;
        Float finalPrice = 30.5f;
        List<PricePersistence> pricePersistenceList = buildPricePersistenceList(dateRange, finalPrice);

        when(priceJpaPersistenceRepositoryMock.findByProductIdAndBrandId(productId, brandId)).thenReturn(pricePersistenceList);
        assertThrows(PriceNotFoundException.class, () -> priceRepository.retrievePrice(date, productId, brandId));
    }

    private List<PricePersistence> buildPricePersistenceList(DateRange dateRange, Float finalPrice) {
        Long priceList1 = 3L;
        Long priceList2 = 4L;
        Float price1 = 23.5f;
        Long priority1 = 0L;
        Long priority2 = 1L;
        PricePersistence pricePersistenceEntity1 = PricePersistence.builder()
                .startDate(dateRange.startDate())
                .endDate(dateRange.endDate())
                .priceList(priceList1)
                .price(price1)
                .priority(priority1)
                .curr(Currency.EUR.toString())
                .build();
        PricePersistence pricePersistenceEntity2 = PricePersistence.builder()
                .startDate(dateRange.startDate())
                .endDate(dateRange.endDate())
                .priceList(priceList2)
                .price(finalPrice)
                .priority(priority2)
                .curr(Currency.EUR.toString())
                .build();
        return Arrays.asList(pricePersistenceEntity1, pricePersistenceEntity2);
    }
}
