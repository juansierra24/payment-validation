package com.bluecoding.payment.controller;

import com.bluecoding.payment.dto.InfoValidationResponse;
import com.bluecoding.payment.dto.PaymentInfoDto;
import com.bluecoding.payment.service.InfoValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.POST})
public class InfoValidationController {

    private final InfoValidationService service;

    @PostMapping("/validate")
    public ResponseEntity<InfoValidationResponse> validate(@Valid @RequestBody PaymentInfoDto paymentInfo){
        boolean isValid = service.validatePaymentInfo(paymentInfo);
        if(isValid){
            InfoValidationResponse response = InfoValidationResponse
                    .builder()
                    .message("Payment information is valid")
                    .status(HttpStatus.OK)
                    .timeStamp(LocalDateTime.now())
                    .build();
            return ResponseEntity.ok(response);
        }else{
            InfoValidationResponse response = InfoValidationResponse
                    .builder()
                    .message("Payment information is invalid")
                    .status(HttpStatus.BAD_REQUEST)
                    .timeStamp(LocalDateTime.now())
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
