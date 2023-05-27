package tech.abede.walletapi.customers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/customers")
    public ResponseEntity<CustomerResponse> createOne(@Valid @RequestBody CustomerRequest customerRequest){
        Customer customer = customerRequest.convertToEntity();
        Customer newCustomer = customerService.createOne(customer);
        CustomerResponse customerResponse = newCustomer.convertToResponse();
        return ResponseEntity.status(201).body(customerResponse);
    }
}
