package com.inditex.challenge.business.port.output;

import com.inditex.challenge.business.domain.entity.Price;

import java.time.LocalDateTime;

public interface IPriceRepository {
    Price retrievePrice(LocalDateTime date, Long productId, Long brandId);

}
