package com.inditex.challenge.presentation.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PriceDtoOut(Long productId, Long brandId, Long priceList, LocalDateTime startDate, LocalDateTime endDate,
                          Float price) {
    public PriceDtoOut {}
}

