package org.example.repository;

import org.example.model.PaymentCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentCardRepository extends JpaRepository<PaymentCard, Long> {
    List<PaymentCard> findByCustomerId(Long customerId);
}
