package tech.abede.walletapi.customers;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.abede.walletapi.applicationuser.ApplicationUser;
import tech.abede.walletapi.wallets.Wallet;


@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String NIK;

    private String name;

    private LocalDate dateOfBirth;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private Wallet wallet;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private ApplicationUser applicationUser;

    public CustomerResponse convertToResponse() {
        return CustomerResponse.builder()
            .walletId(wallet.getId())
            .balance(wallet.getBalance())
            .email(applicationUser.getEmail())
            .username(applicationUser.getUsername())
            .build();
    }
 
}
