package com.inditex.challenge.business.port.input;

import com.inditex.challenge.business.domain.entity.Price;

import java.time.LocalDateTime;

public interface IPriceService {

    Price retrievePrice(LocalDateTime date, Long productId, Long brandId);
}
