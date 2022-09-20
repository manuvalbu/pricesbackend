package com.inditex.challenge.business.service;

import com.inditex.challenge.business.domain.entity.Price;
import com.inditex.challenge.business.port.input.IPriceService;
import com.inditex.challenge.business.port.output.IPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PriceService implements IPriceService {
    private final IPriceRepository priceRepository;

    @Override
    public Price retrievePrice(LocalDateTime date, Long productId, Long brandId) {
        return priceRepository.retrievePrice(date, productId, brandId);
    }
}
