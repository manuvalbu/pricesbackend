package com.inditex.challenge.business.port.input;

import com.inditex.challenge.presentation.dto.PriceDtoOut;

import java.time.LocalDateTime;

public interface IPriceService {

    PriceDtoOut retrievePrice(LocalDateTime date, Long productId, Long brandId);
}
