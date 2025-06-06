package com.codewithmosh.store.payments;

import com.codewithmosh.store.common.ErrorDto;
import com.codewithmosh.store.carts.CartEmptyException;
import com.codewithmosh.store.carts.CartNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/checkout")
@RequiredArgsConstructor
@Tag(name = "Checkout")
public class CheckoutController {
    private final CheckoutService checkoutService;


    @PostMapping
    @Operation(summary = "Create a checkout session")
    public CheckoutResponse checkout(@Valid @RequestBody CheckoutRequest request) {
        return checkoutService.checkout(request);
    }


    @PostMapping("/webhook")
    @Operation(summary = "Handle a webhook event")
    public void handleWebhook(
            @RequestHeader Map<String, String> headers,
            @RequestBody String payload
    ) {
        checkoutService.handleWebhookEvent(new WebhookRequest(headers, payload));
    }

    @ExceptionHandler({CartNotFoundException.class, CartEmptyException.class})
    public ResponseEntity<ErrorDto> handleException(Exception ex) {
        return ResponseEntity.badRequest().body(new ErrorDto(ex.getMessage()));
    }

    @ExceptionHandler(PaymentException.class)
    public ResponseEntity<Object> handlePaymentException() {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDto("Error creating a checkout session"));

    }
}
