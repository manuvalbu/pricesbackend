package com.indirex.challenge.it;

import com.inditex.challenge.business.exception.PriceNotFoundException;
import com.inditex.challenge.business.port.input.IPriceService;
import com.inditex.challenge.presentation.controller.PriceController;
import com.inditex.challenge.presentation.dto.PriceDtoOut;
import com.inditex.challenge.presentation.exception.ExceptionController;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;

import static com.inditex.challenge.presentation.controller.PriceController.BASE_PATH;
import static com.inditex.challenge.presentation.controller.PriceController.PRICE_PATH;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PriceApiControllerTest {

    private MockMvc mockMvc;
    PriceController priceController;
    @Mock
    IPriceService priceServiceMock;

    @BeforeEach
    void init() {
        this.priceController = new PriceController(priceServiceMock);
        this.mockMvc = MockMvcBuilders.standaloneSetup(priceController).setControllerAdvice(ExceptionController.class).build();
    }

    @Test
    void getPriceOk() throws Exception {
        LocalDateTime date = LocalDateTime.of(2020, 6, 15, 0, 0, 0);
        Long productId = 35455L;
        Long brandId = 1L;
        Long priceList = 3L;
        Float price = 23.5f;
        PriceDtoOut priceDtoOut = PriceDtoOut.builder().priceList(priceList).price(price).build();

        when(priceServiceMock.retrievePrice(date, productId, brandId)).thenReturn(priceDtoOut);

        final String path = UriComponentsBuilder.fromPath(BASE_PATH + PRICE_PATH)
                .queryParam("date", date)
                .queryParam("product_id", productId)
                .queryParam("brand_id", brandId).toUriString();

        this.mockMvc.perform(MockMvcRequestBuilders.get(path))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceList").value(priceList))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(price));

        verify(priceServiceMock, times(1)).retrievePrice(date, productId, brandId);
    }

    @Test
    void getPriceKoPriceNotFound() throws Exception {
        LocalDateTime date = LocalDateTime.of(2020, 6, 15, 0, 0, 0);
        Long productId = 35455L;
        Long brandId = 1L;
        PriceNotFoundException priceNotFoundException = new PriceNotFoundException(date, productId, brandId);

        when(priceServiceMock.retrievePrice(date, productId, brandId)).thenThrow(priceNotFoundException);

        final String path = UriComponentsBuilder.fromPath(BASE_PATH + PRICE_PATH)
                .queryParam("date", date)
                .queryParam("product_id", productId)
                .queryParam("brand_id", brandId).toUriString();

        this.mockMvc.perform(MockMvcRequestBuilders.get(path))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string(Matchers.containsStringIgnoringCase(productId.toString())));

        verify(priceServiceMock, times(1)).retrievePrice(date, productId, brandId);
    }
}
