package com.inditex.challenge.business.service;

import com.inditex.challenge.business.port.input.IPriceService;
import com.inditex.challenge.presentation.dto.PriceDtoOut;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PriceService implements IPriceService {
    @Override
    public PriceDtoOut retrievePrice(LocalDateTime date, Long productId, Long brandId) {
        return null;
    }
}
