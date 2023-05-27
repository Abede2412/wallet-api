package tech.abede.walletapi.customers;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerInfo {

    private String nik;

    private String name;

    private LocalDate dateOfBirth;

    private String username;

    private String email;

    private Double walletBalance;

}
