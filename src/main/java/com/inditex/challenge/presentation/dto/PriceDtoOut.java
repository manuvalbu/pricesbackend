package com.inditex.challenge.presentation.dto;

import com.inditex.challenge.business.domain.vo.Currency;
import lombok.Builder;

@Builder
public record PriceDtoOut(Long productId,
                          Long brandId,
                          Long priceList,
                          String startDate,
                          String endDate,
                          Float price,
                          Currency curr) {
    public PriceDtoOut {
        // For working with lombok builder
    }
}

