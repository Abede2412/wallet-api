package tech.abede.walletapi.customers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
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
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String NIK;

    private String name;

    private LocalDate dateOfBirth;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "customer")
    @Cascade(CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Wallet wallet;

    @OneToOne(mappedBy = "customer")
    @Cascade(CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private ApplicationUser applicationUser;

    public CustomerResponse convertToResponse() {
        return CustomerResponse.builder()
            .walletId(wallet.getId())
            .balance(wallet.getBalance())
            .email(applicationUser.getEmail())
            .username(applicationUser.getUsername())
            .build();
    }

    public CustomerInfo info(){
        return CustomerInfo.builder()
            .nik(NIK)
            .name(name)
            .dateOfBirth(dateOfBirth)
            .username(applicationUser.getUsername())
            .email(applicationUser.getEmail())
            .walletBalance(wallet.getBalance())
            .build();
    }

 
}
