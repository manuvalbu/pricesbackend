package com.inditex.challenge.presentation.mapper;

import com.inditex.challenge.business.domain.entity.Price;
import com.inditex.challenge.presentation.dto.PriceDtoOut;

import java.time.format.DateTimeFormatter;

public class PriceDomainPresentationMapper {

    public PriceDtoOut convertToDtoOut(Price price) {
        return PriceDtoOut.builder()
                .productId(price.productId())
                .brandId(price.brandId())
                .priceList(price.priceList())
                .startDate(price.dateRange().startDate().format(DateTimeFormatter.ISO_DATE_TIME))
                .endDate(price.dateRange().endDate().format(DateTimeFormatter.ISO_DATE_TIME))
                .price(price.price())
                .curr(price.curr())
                .build();
    }
}
