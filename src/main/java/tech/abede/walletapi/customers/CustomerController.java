package tech.abede.walletapi.customers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import tech.abede.walletapi.applicationuser.ApplicationUser;
import tech.abede.walletapi.applicationuser.ApplicationUserService;
import tech.abede.walletapi.authentication.model.UserPrincipal;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final ApplicationUserService applicationUserService;

    @PostMapping("/customers")
    public ResponseEntity<CustomerResponse> createOne(@Valid @RequestBody CustomerRequest customerRequest){
        Customer customer = customerRequest.convertToEntity();
        Customer newCustomer = customerService.createOne(customer);
        customerService.topUp(newCustomer, customerRequest.getFirstTopUp());
        CustomerResponse customerResponse = newCustomer.convertToResponse();
        return ResponseEntity.status(201).body(customerResponse);
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/customers")
    public ResponseEntity<CustomerInfo> getCustomerInfo(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Customer customer = customerService.getOneByid(userPrincipal.getId());

        return ResponseEntity.ok().body(customer.info());
    }
}
