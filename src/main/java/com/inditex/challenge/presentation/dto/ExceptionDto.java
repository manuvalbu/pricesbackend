package com.inditex.challenge.presentation.dto;

import lombok.Builder;

@Builder
public record ExceptionDto(String code,
                           String message) {
    public ExceptionDto {
    }
}
