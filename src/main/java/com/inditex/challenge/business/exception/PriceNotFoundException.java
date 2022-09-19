package com.inditex.challenge.business.exception;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PriceNotFoundException extends RuntimeException {
    private final LocalDateTime date;
    private final Long productId;
    private final Long brandId;

    public PriceNotFoundException(LocalDateTime date, Long productId, Long brandId) {
        super("Price not found for productId " + productId + " brandId " + brandId + " and date " + date);
        this.date = date;
        this.productId = productId;
        this.brandId = brandId;
    }
}
