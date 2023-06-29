package com.Payment.Payment.rest.request;


import lombok.Data;

import java.math.BigDecimal;


public class UtilityPaymentRequest {
	private Long providerId;
    private BigDecimal amount;
    private String referenceNumber;
    private String account;
  
}
