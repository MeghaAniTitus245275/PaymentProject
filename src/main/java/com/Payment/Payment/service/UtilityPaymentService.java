package com.Payment.Payment.service;

import com.Payment.Payment.model.TransactionStatus;
import com.Payment.Payment.model.dto.UtilityPayment;
import com.Payment.Payment.model.entity.UtilityPaymentEntity;
import com.Payment.Payment.model.mapper.UtilityPaymentMapper;
import com.Payment.Payment.repository.UtilityPaymentRepository;
import com.Payment.Payment.rest.request.UtilityPaymentRequest;
import com.Payment.Payment.rest.response.UtilityPaymentResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UtilityPaymentService {
    private final UtilityPaymentRepository utilityPaymentRepository;
    private final UtilityPaymentMapper utilityPaymentMapper = new UtilityPaymentMapper();
    private final Logger log = LoggerFactory.getLogger(UtilityPaymentService.class);

    public UtilityPaymentResponse utilPayment(UtilityPaymentRequest paymentRequest) {
        log.info("Utility payment processing {}", paymentRequest.toString());

        UtilityPaymentEntity entity = new UtilityPaymentEntity();
        BeanUtils.copyProperties(paymentRequest, entity);
        entity.setStatus(TransactionStatus.PROCESSING);
        UtilityPaymentEntity optUtilPayment = utilityPaymentRepository.save(entity);

        String transactionId = UUID.randomUUID().toString();
        optUtilPayment.setStatus(TransactionStatus.SUCCESS);
        optUtilPayment.setTransactionId(transactionId);

        utilityPaymentRepository.save(optUtilPayment);

        return UtilityPaymentResponse.builder()
                .message("Utility Payment Successfully Processed")
                .transactionId(transactionId)
                .build();
    }

    public List<UtilityPayment> readPayments(Pageable pageable) {
        Page<UtilityPaymentEntity> allUtilPayments = utilityPaymentRepository.findAll(pageable);
        return utilityPaymentMapper.convertToDtoList(allUtilPayments.getContent());
    }
}
