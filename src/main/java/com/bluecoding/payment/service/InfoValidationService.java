package com.bluecoding.payment.service;

import com.bluecoding.payment.dto.PaymentInfoDto;

public interface InfoValidationService {
    public boolean validatePaymentInfo(PaymentInfoDto dto);
}
