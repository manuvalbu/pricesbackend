package com.inditex.challenge.presentation.controller;

import com.inditex.challenge.business.port.input.IPriceService;
import com.inditex.challenge.presentation.dto.PriceDtoOut;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RequestMapping(PriceController.BASE_PATH)
@RestController
@RequiredArgsConstructor
public class PriceController {

    public static final String BASE_PATH = "/challenge";
    public static final String PRICE_PATH = "/price";

    private final IPriceService priceService;

    @GetMapping(PRICE_PATH)
    public ResponseEntity<PriceDtoOut> getPrice(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam("product_id") Long productId,
            @RequestParam("brand_id") Long brandId) {
        log.info("getting Price");
        PriceDtoOut priceDtoOut = priceService.retrievePrice(date, productId, brandId);
        log.info("successfully get price");
        return ResponseEntity.ok(priceDtoOut);
    }
}
