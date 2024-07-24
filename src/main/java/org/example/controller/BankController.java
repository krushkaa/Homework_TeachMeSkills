package org.example.controller;

import org.example.model.Customer;
import org.example.model.PaymentCard;
import org.example.repository.CustomerRepository;
import org.example.repository.PaymentCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BankController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PaymentCardRepository paymentCardRepository;

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/customer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @GetMapping("/payment-card/{id}")
    public ResponseEntity<PaymentCard> getPaymentCard(@PathVariable Long id) {
        Optional<PaymentCard> paymentCard = paymentCardRepository.findById(id);
        return paymentCard.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/payment-card")
    public ResponseEntity<PaymentCard> createPaymentCard(@RequestBody PaymentCard paymentCard) {
        PaymentCard savedPaymentCard = paymentCardRepository.save(paymentCard);
        return new ResponseEntity<>(savedPaymentCard, HttpStatus.CREATED);
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestParam Long fromCardId, @RequestParam Long toCardId, @RequestParam double amount) {
        Optional<PaymentCard> fromCardOpt = paymentCardRepository.findById(fromCardId);
        Optional<PaymentCard> toCardOpt = paymentCardRepository.findById(toCardId);

        if (fromCardOpt.isPresent() && toCardOpt.isPresent()) {
            PaymentCard fromCard = fromCardOpt.get();
            PaymentCard toCard = toCardOpt.get();

            if (fromCard.getBalance() >= amount) {
                fromCard.setBalance(fromCard.getBalance() - amount);
                toCard.setBalance(toCard.getBalance() + amount);
                paymentCardRepository.save(fromCard);
                paymentCardRepository.save(toCard);
                return ResponseEntity.ok("Transfer successful");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient balance");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("One or both cards not found");
        }
    }
}
