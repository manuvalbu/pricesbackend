package com.indirex.challenge.ut;

import com.inditex.challenge.business.domain.entity.Price;
import com.inditex.challenge.business.domain.vo.DateRange;
import com.inditex.challenge.business.port.output.IPriceRepository;
import com.inditex.challenge.business.service.PriceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PrimeServiceTest {

    @InjectMocks
    PriceService priceService;

    @Mock
    IPriceRepository priceRepositoryMock;

    @Test
    void retrievePriceOkUTest() {
        LocalDateTime date = LocalDateTime.now();
        Long productId = 35455L;
        Long brandId = 1L;
        Price priceDomainEntity = Price.builder().build();

        when(priceRepositoryMock.retrievePrice(date, productId, brandId)).thenReturn(priceDomainEntity);
        assertEquals(priceDomainEntity, priceService.retrievePrice(date, productId, brandId));
        verify(priceRepositoryMock, times(1)).retrievePrice(date, productId, brandId);
    }
}
