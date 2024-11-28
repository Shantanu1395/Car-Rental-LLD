package CarRental.example.CarRentalLLD.controllers;

import CarRental.example.CarRentalLLD.dtos.PaymentRequestDTO;
import CarRental.example.CarRentalLLD.interfaces.PaymentService;
import CarRental.example.CarRentalLLD.models.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping()
    public ResponseEntity<Payment> processPayment(@RequestBody PaymentRequestDTO paymentRequest) {
        Payment payment = paymentService.processPayment(
                paymentRequest.getBookingId(),
                paymentRequest.getAmount(),
                paymentRequest.getPaymentMethod()
        );
        return new ResponseEntity<>(payment, HttpStatus.CREATED);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<List<Payment>> getPaymentsForBooking(@PathVariable Long bookingId) {
        List<Payment> payments = paymentService.getPaymentsForBooking(bookingId);
        return ResponseEntity.ok(payments);
    }
}
