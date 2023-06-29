package com.Payment.Payment.repository;

import com.Payment.Payment.model.entity.UtilityPaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilityPaymentRepository extends JpaRepository<UtilityPaymentEntity, Long> {
    // You can add custom query methods here if needed
}
