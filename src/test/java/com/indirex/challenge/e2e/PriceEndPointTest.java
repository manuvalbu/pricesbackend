package com.indirex.challenge.e2e;

import com.inditex.challenge.Application;
import com.inditex.challenge.presentation.dto.PriceDtoOut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
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
import java.util.stream.Stream;

import static com.inditex.challenge.presentation.controller.PriceController.BASE_PATH;
import static com.inditex.challenge.presentation.controller.PriceController.PRICE_PATH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PriceEndPointTest {
    @LocalServerPort
    int randomServerPort;
    RestTemplate restTemplate = new RestTemplate();
    static final String BASE_URL = "http://localhost:";

    Long productId;
    Long brandId;


    @BeforeEach
    void setUp() {
        productId = 35455L;
        brandId = 1L;
    }

    @ParameterizedTest
    @MethodSource("priceInputs")
    void getPriceOKE2ETest(LocalDateTime date, Long priceListResult, Float priceResult) {

        final String uri = UriComponentsBuilder.fromHttpUrl(BASE_URL + randomServerPort + BASE_PATH + PRICE_PATH)
                .queryParam("date", date.toString())
                .queryParam("product_id", productId)
                .queryParam("brand_id", brandId).toUriString();

        ResponseEntity<PriceDtoOut> responseEntity = restTemplate.exchange(uri, HttpMethod.GET,
                new HttpEntity<>(null, buildHeaders()), PriceDtoOut.class);
        PriceDtoOut priceOut = responseEntity.getBody();

        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        assertEquals(priceListResult, priceOut.priceList());
        assertEquals(priceResult, priceOut.price());
    }

    private HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }

    private static Stream<Arguments> priceInputs() {
        return Stream.of(
                arguments(LocalDateTime.of(2020, 6, 14, 10, 0, 0), 1L, 35.5f),
                arguments(LocalDateTime.of(2020, 6, 14, 16, 0, 0), 2L, 25.45f),
                arguments(LocalDateTime.of(2020, 6, 14, 21, 0, 0), 1L, 35.5f),
                arguments(LocalDateTime.of(2020, 6, 15, 10, 0, 0), 3L, 30.5f),
                arguments(LocalDateTime.of(2020, 6, 16, 21, 0, 0), 4L, 38.95f)
        );
    }
}
