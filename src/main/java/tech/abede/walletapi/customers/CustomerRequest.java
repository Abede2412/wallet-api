package tech.abede.walletapi.customers;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.abede.walletapi.applicationuser.ApplicationUser;
import tech.abede.walletapi.wallets.Wallet;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {

    @NotBlank
    private String name;

    @Size(min = 16, max = 16)
    private String NIK;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate dateOfBirth;

    @NotBlank
    private String username;

    @Email
    private String email;

    @NotBlank
    private String password;

    public Customer convertToEntity(){

        Customer customer = Customer.builder()
            .NIK(NIK)
            .name(name)
            .dateOfBirth(dateOfBirth)
            .build();

        ApplicationUser applicationUser = ApplicationUser.builder()
            .email(email)
            .username(username)
            .password(password)
            .build();

        Wallet wallet = Wallet.builder()
            .balance(100000.0)
            .build();
        
        applicationUser.setCustomer(customer);
        customer.setApplicationUser(applicationUser);
        customer.setWallet(wallet);
        return customer;
    }
}
