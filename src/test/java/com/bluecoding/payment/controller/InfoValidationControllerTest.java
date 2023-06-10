package com.bluecoding.payment.controller;

import com.bluecoding.payment.dto.PaymentInfoDto;
import com.bluecoding.payment.service.InfoValidationService;
import com.bluecoding.payment.service.InfoValidationServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.validator.internal.util.logging.Messages_$bundle;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import com.google.gson.Gson;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;


@WebMvcTest(InfoValidationController.class)
class InfoValidationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    InfoValidationServiceImpl infoValidationService;

    @InjectMocks
    InfoValidationController controller;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void givenBlankFields_whenValidatePaymentInfo_thenReturnBadRequest() throws Exception{
        PaymentInfoDto dto = PaymentInfoDto.builder()
                .cardName("").cardNumber("").cvv("").
                expirationDate(LocalDate.now()).build();

        when(infoValidationService.validatePaymentInfo(any())).thenReturn(true);

        mockMvc.perform(post("/api/v1/payment/validate")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());

        verify(infoValidationService, times(0)).validatePaymentInfo(any());
    }

    @Test
    void givenValidPaymentDto_whenServiceReturnsTrue_thenReturnOk() throws Exception{
        PaymentInfoDto dto = PaymentInfoDto.builder()
                .cardName("Juan Carlos").cardNumber("12341234123412341").cvv("123").
                expirationDate(LocalDate.now()).build();

        when(infoValidationService.validatePaymentInfo(dto)).thenReturn(true);

        mockMvc.perform(post("/api/v1/payment/validate")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Payment information is valid"));

        verify(infoValidationService).validatePaymentInfo(dto);
    }

    @Test
    void givenValidPaymentDto_whenServiceReturnsFalse_thenReturnBadRequest() throws Exception{
        PaymentInfoDto dto = PaymentInfoDto.builder()
                .cardName("Juan Carlos").cardNumber("12341234123412341").cvv("123").
                expirationDate(LocalDate.now()).build();

        when(infoValidationService.validatePaymentInfo(dto)).thenReturn(false);

        mockMvc.perform(post("/api/v1/payment/validate")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Payment information is invalid"));

        verify(infoValidationService).validatePaymentInfo(dto);
    }
}