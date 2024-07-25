package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.model.Customer;
import org.example.model.PaymentCard;
import org.example.repository.CustomerRepository;
import org.example.repository.PaymentCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name = "Bank controller",
        description = "Contains methods for creating and getting clients, payment cards, and transfers between them.")
@RestController
@RequestMapping("/api")
public class BankController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PaymentCardRepository paymentCardRepository;

    @Operation(summary = "Getting client by id")
    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Creating a new client")
    @PostMapping("/customer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @Operation(summary = "Getting card by id")
    @GetMapping("/payment-card/{id}")
    public ResponseEntity<PaymentCard> getPaymentCard(@PathVariable Long id) {
        Optional<PaymentCard> paymentCard = paymentCardRepository.findById(id);
        return paymentCard.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Creating a new card")
    @PostMapping("/payment-card")
    public ResponseEntity<PaymentCard> createPaymentCard(@RequestBody PaymentCard paymentCard) {
        PaymentCard savedPaymentCard = paymentCardRepository.save(paymentCard);
        return new ResponseEntity<>(savedPaymentCard, HttpStatus.CREATED);
    }

    @Operation(summary = "Do a transfer between clients")
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
