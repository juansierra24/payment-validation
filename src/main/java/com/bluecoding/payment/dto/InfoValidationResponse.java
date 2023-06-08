package com.bluecoding.payment.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class InfoValidationResponse {
    private String message;
    private LocalDateTime timeStamp;
    private HttpStatus status;
}
