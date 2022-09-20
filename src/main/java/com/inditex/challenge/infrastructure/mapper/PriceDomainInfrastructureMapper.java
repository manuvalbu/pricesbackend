package com.inditex.challenge.infrastructure.mapper;

import com.inditex.challenge.business.domain.entity.Price;
import com.inditex.challenge.business.domain.vo.Currency;
import com.inditex.challenge.business.domain.vo.DateRange;
import com.inditex.challenge.infrastructure.entity.PricePersistence;

public class PriceDomainInfrastructureMapper {

    public Price convertToPrice(PricePersistence pricePersistence) {
        return Price.builder()
                .priceId(pricePersistence.getPriceId())
                .brandId(pricePersistence.getBrandId())
                .priceList(pricePersistence.getPriceList())
                .productId(pricePersistence.getProductId())
                .dateRange(new DateRange(pricePersistence.getStartDate(), pricePersistence.getEndDate()))
                .price(pricePersistence.getPrice())
                .curr(Currency.valueOf(pricePersistence.getCurr()))
                .build();
    }
}
