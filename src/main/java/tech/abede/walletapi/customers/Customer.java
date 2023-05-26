package tech.abede.walletapi.customers;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.abede.walletapi.wallets.Wallet;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    private String NIK;

    private String name;

    private LocalDateTime dateOfBirth;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToOne
    private Wallet wallet;
 
}
