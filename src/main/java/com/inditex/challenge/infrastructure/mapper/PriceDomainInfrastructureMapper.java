package com.inditex.challenge.infrastructure.mapper;

import com.inditex.challenge.business.domain.entity.Price;
import com.inditex.challenge.business.domain.vo.DateRange;
import com.inditex.challenge.infrastructure.entity.PricePersistence;

public class PriceDomainInfrastructureMapper {

    public Price convertToPrice(PricePersistence pricePersistence) {
        return Price.builder()
                .productId(pricePersistence.getProductId())
                .brandId(pricePersistence.getBrandId())
                .priceList(pricePersistence.getPriceList())
                .dateRange(new DateRange(pricePersistence.getStartDate(), pricePersistence.getEndDate()))
                .price(pricePersistence.getPrice())
                .curr(pricePersistence.getCurr())
                .build();
    }
}
