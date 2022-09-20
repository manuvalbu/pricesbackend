package com.inditex.challenge.business.domain.entity;

import com.inditex.challenge.business.domain.vo.Currency;
import com.inditex.challenge.business.domain.vo.DateRange;
import lombok.Builder;

@Builder
public record Price(Long brandId,
                    DateRange dateRange,
                    Long priceList,
                    Long productId,
                    Long priority,
                    Float price,
                    Currency curr) {
    public Price {
        // For working with lombok builder
    }
}
