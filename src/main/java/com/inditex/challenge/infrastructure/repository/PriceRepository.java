package com.inditex.challenge.infrastructure.repository;

import com.inditex.challenge.business.domain.entity.Price;
import com.inditex.challenge.business.exception.PriceNotFoundException;
import com.inditex.challenge.business.port.output.IPriceRepository;
import com.inditex.challenge.infrastructure.entity.PricePersistence;
import com.inditex.challenge.infrastructure.mapper.PriceDomainInfrastructureMapper;
import com.inditex.challenge.infrastructure.persistence.IPriceJpaPersistenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PriceRepository implements IPriceRepository {

    private final IPriceJpaPersistenceRepository priceJpaRepository;

    @Override
    public Price retrievePrice(LocalDateTime date, Long productId, Long brandId) {

        List<PricePersistence> pricePersistenceList = priceJpaRepository.findByProductIdAndBrandId(productId, brandId);

        PricePersistence pricePersistenceEntity = pricePersistenceList.stream().
                filter(pricePersistence -> pricePersistence.getStartDate().minusNanos(1).isBefore(date)
                        && pricePersistence.getEndDate().plusNanos(1).isAfter(date))
                .max(Comparator.comparing(PricePersistence::getPriority))
                .orElseThrow(() -> new PriceNotFoundException(date, productId, brandId));

        return new PriceDomainInfrastructureMapper().convertToPrice(pricePersistenceEntity);
    }
}
