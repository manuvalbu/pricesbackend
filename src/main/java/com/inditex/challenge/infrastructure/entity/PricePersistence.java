package com.inditex.challenge.infrastructure.entity;

import com.inditex.challenge.business.domain.vo.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "PRICE")
public class PricePersistence {
    Long productId;
    Long brandId;
    LocalDateTime startDate;
    LocalDateTime endDate;
    Long priceList;
    Long priority;
    Float price;
    Currency curr;
}
