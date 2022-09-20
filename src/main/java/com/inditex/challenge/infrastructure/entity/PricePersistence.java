package com.inditex.challenge.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "PRICES")
public class PricePersistence {
    @Id
    Long priceId;
    Long brandId;
    LocalDateTime startDate;
    LocalDateTime endDate;
    Long priceList;
    Long productId;
    Long priority;
    Float price;
    String curr;
}
