package com.indirex.challenge.e2e;

import com.inditex.challenge.Application;
import com.inditex.challenge.presentation.dto.PriceDtoOut;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.Collections;

import static com.inditex.challenge.presentation.controller.PriceController.BASE_PATH;
import static com.inditex.challenge.presentation.controller.PriceController.PRICE_PATH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PriceEndPointTest {
    @LocalServerPort
    int randomServerPort;
    RestTemplate restTemplate = new RestTemplate();
    static final String BASE_URL = "http://localhost:";

    @Test
    void getPriceOKTest() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 15, 0, 0, 0);
        Long productId = 35455L;
        Long brandId = 1L;
        Long priceList = 3L;
        Float price = 23.5f;

        final String uri = UriComponentsBuilder.fromHttpUrl(BASE_URL + randomServerPort + BASE_PATH + PRICE_PATH)
                .queryParam("date", date.toString())
                .queryParam("product_id", productId)
                .queryParam("brand_id", brandId).toUriString();

        ResponseEntity<PriceDtoOut> responseEntity = restTemplate.exchange(uri, HttpMethod.GET,
                new HttpEntity<>(null, buildHeaders()), PriceDtoOut.class);
        PriceDtoOut priceOut = responseEntity.getBody();

        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        assertEquals(priceList, priceOut.priceList());
        assertEquals(price, priceOut.price());
    }

    private HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }
}
