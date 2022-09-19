package com.inditex.challenge.business.domain.vo;

import java.time.LocalDateTime;

public record DateRange(LocalDateTime startDate,
                        LocalDateTime endDate) {
}
